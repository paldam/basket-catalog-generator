package com.damian.service.util;

import com.damian.domain.Basket;
import com.damian.domain.CatalogArchive;
import com.damian.domain.Product;
import com.damian.service.MailService;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;


public class CatalogGenerator {


    private static final Logger log = LoggerFactory.getLogger(CatalogGenerator.class);


    private static final String FONT_SEGOPR = "themes/fonts/segoepr.ttf" ;
    private static final String FONT_ROBOTO_ITALIC = "themes/fonts/Roboto-Italic.ttf" ;
    private static final String FONT_ROBOTO_BOLD = "themes/fonts/Roboto-Bold.ttf" ;
    private static final String FONT_ROBOTO_REGULAR = "themes/fonts/Roboto-Regular.ttf" ;


    public static ByteArrayInputStream generateCatalog(CatalogArchive catalog) throws IOException {

        String theme = catalog.getCatalogTheme().name();

        String FIRST_PAGE_IMAGE = "themes/" +theme + "/obraz.jpg";
        String CONTENT_PAGE_TOP_BACKGROUND = "themes/" +theme + "/belka.jpg";



        String THEME_FONT = "themes/" +theme + "/czcionka.ttf";

        String BASKET_EXAMPLE = "themes/fonts/kosz.png" ;


        try{
            PdfFont fontTheme= PdfFontFactory.createFont(THEME_FONT, "Cp1250", true);
        }catch(com.itextpdf.io.IOException ex){
            THEME_FONT = "themes/" +theme + "/czcionka.otf";
            PdfFont fontTheme= PdfFontFactory.createFont(THEME_FONT, "Cp1250", true);
        }



        PdfFont fontTheme= PdfFontFactory.createFont(THEME_FONT, "Cp1250", true);

        log.error("DDDDDDDD" + catalog.toString());

        PdfFont fontSegopr = PdfFontFactory.createFont(FONT_SEGOPR, "Cp1250", true);
        PdfFont fontRobotoItalic = PdfFontFactory.createFont(FONT_ROBOTO_ITALIC, "Cp1250", true);
        PdfFont fontRobotoBold = PdfFontFactory.createFont(FONT_ROBOTO_BOLD, "Cp1250", true);
        PdfFont fontRobotoRegular = PdfFontFactory.createFont(FONT_ROBOTO_REGULAR, "Cp1250", true);

        Color dBordowy = new DeviceRgb(147, 28, 0);
        Color dSzary = new DeviceRgb(96, 96, 96);
        Color dZloty = new DeviceRgb(198, 156, 109);


        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(out));
        PageSize pageSize = new PageSize(1366, 768);
        Document doc = new Document(pdfDoc, pageSize);
        doc.setMargins(0, 0, 0, 0);
        PdfCanvas canvas = new PdfCanvas(pdfDoc.addNewPage());




        Image img = new Image(ImageDataFactory.create(FIRST_PAGE_IMAGE));
        img.setFixedPosition(475, 0);
        img.setHeight(900);
        img.setWidth(891);





        if (catalog.getLogo() != null){
            Image logoImg = new Image(ImageDataFactory.create(catalog.getLogo()));
            logoImg.setFixedPosition(40, 450);
            logoImg.setHeight(300);
            logoImg.setWidth(400);
            setBorderForAllElement(logoImg);
            doc.add(logoImg);
        }








        Text catalogName = new Text(catalog.getCatalogName())
            .setFont(fontTheme)
            .setFontSize(37)
            .setFontColor(dBordowy)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);




        Paragraph p = new Paragraph().add(catalogName);


        setBorderForAllElement(p);
        p.setHeight(120);
        p.setWidth(408);
        p.setFixedLeading(40f);
        p.setFixedPosition(30, 275, 350);

        Text offertText = new Text("Oferta przygotowana dla firmy:")
            .setFont(fontRobotoItalic)
            .setFontSize(20)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph p2 = new Paragraph().add(offertText)
            .setFixedPosition(30, 230, 350);
        setBorderForAllElement(p2)
            .setHeight(40)
            .setWidth(408)
            .setFixedLeading(40f);

        Text customer = new Text("");
        if (catalog.getForWho() == null) {

            customer = new Text(" ");
        }else{
           customer = new Text(catalog.getForWho());

        }

        customer
            .setFont(fontRobotoBold)
            .setFontSize(36)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);


        Paragraph p3 = new Paragraph().add(customer)
            .setFixedPosition(30, 165, 390);
        setBorderForAllElement(p3)
            .setHeight(80)
            .setWidth(408)
            .setFixedLeading(40f);


        if (catalog.getForWho() != null) {

            doc.add(p2);
            doc.add(p3);
        }




        Text customeAssistant = new Text("Opiekun klienta:")
            .setFont(fontRobotoItalic)
            .setFontSize(20)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph p4 = new Paragraph().add(customeAssistant)
            .setFixedPosition(30, 120, 350);
        setBorderForAllElement(p4)
            .setHeight(50)
            .setWidth(408)
            .setFixedLeading(40f);


        Text assistantName = new Text("");

        if (catalog.getCustomerAssistantName() == null) {

            assistantName = new Text(" ");
        }else{
            assistantName = new Text(catalog.getCustomerAssistantName());

        }

        assistantName
            .setFont(fontRobotoRegular)
            .setFontSize(20)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph p5 = new Paragraph().add(assistantName)
            .setFixedPosition(30, 100, 350);
        setBorderForAllElement(p5)
            .setWidth(408)
            .setFixedLeading(40f);
        setBorderForAllElement(p5);


        Text assistantEmail = new Text("");

        if (catalog.getCustomerAssistantEmail() == null) {

            assistantEmail = new Text(" ");
        }else{
            assistantEmail = new Text(catalog.getCustomerAssistantEmail());

        }

        assistantEmail
            .setFont(fontRobotoRegular)
            .setFontSize(20)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph p6 = new Paragraph().add(assistantEmail)
            .setFixedPosition(30, 60, 350)
            .setHeight(50)
            .setWidth(408)
            .setFixedLeading(40f);
        setBorderForAllElement(p6);




        Text assistantTel = new Text("");

        if (catalog.getCustomerAssistantTel() == null) {

            assistantTel = new Text(" ");
        }else{
            assistantTel = new Text(catalog.getCustomerAssistantTel());

        }


       assistantTel
            .setFont(fontRobotoRegular)
            .setFontSize(20)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph p7 = new Paragraph().add(assistantTel)
            .setFixedPosition(30, 30, 350)
            .setHeight(50)
            .setWidth(408)
            .setFixedLeading(40f);
        setBorderForAllElement(p7);
        /////////////////////////////////////////////////



        if (catalog.getCustomerAssistantTel() == null ||  catalog.getCustomerAssistantEmail()== null || catalog.getCustomerAssistantName() ==null   ){

        }else{
            doc.add(p4);
            doc.add(p5);
            doc.add(p6);
            doc.add(p7);
        }


        doc.add(img);
        doc.add(p);




        canvas.setStrokeColor(dZloty);
        canvas.moveTo(510, 730);
        canvas.lineTo(1330, 730).lineTo(1330, 35).lineTo(510, 35).setLineWidth(3f);
        canvas.closePathStroke();



         Set<Basket> basketList = catalog.getBaskets();
           int currentPosition = 0;
            for (Basket basket : basketList) {
                log.error("Noa strona ____________________________ "  );

                PdfCanvas canvasPage2 = new PdfCanvas(pdfDoc.addNewPage());
                doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));


                Image topImage = new Image(ImageDataFactory.create(CONTENT_PAGE_TOP_BACKGROUND));
                topImage.setFixedPosition(0, 669);
                topImage.setHeight(99);
                topImage.setWidth(1366);


                Text catalogNameContent = new Text(catalog.getCatalogName())
                    .setFont(fontTheme)
                    .setFontColor(Color.WHITE)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);





                if(!theme.equals("Motyw_10") ){
                    catalogNameContent.setFontSize(60);

                }else{
                    catalogNameContent.setFontSize(49);

                }



                Paragraph catalogNameContentParagraph = new Paragraph().add(catalogNameContent);


                if(!theme.equals("Motyw_10")){
                    catalogNameContentParagraph.setFixedPosition(40, 650, 1330);

                }else{
                    catalogNameContentParagraph.setFixedPosition(40, 660, 1330);

                }

                if(theme.equals("Motyw_5")){
                    catalogNameContentParagraph.setFixedPosition(40, 670, 1330);

                }


                setBorderForAllElement(catalogNameContentParagraph);
                catalogNameContentParagraph.setHeight(120);
                catalogNameContentParagraph.setWidth(1366);


                StringBuilder sb = new StringBuilder();
                sb.append("basket-image/");
                sb.append(basket.getOrginBasketId().toString());
                sb.append(".jpg");
                String BASKET_IMG = sb.toString();

                Image BasketImage = new Image(ImageDataFactory.create(BASKET_IMG));
                BasketImage.setFixedPosition(43, 35);
                BasketImage.setHeight(600);
                BasketImage.setWidth(600);


                Text basketName = new Text(basket.getBasketName())
                    .setFont(fontTheme)
                    .setFontSize(40)
                    .setFontColor(dBordowy)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);

                Paragraph basketNameParagraph = new Paragraph().add(basketName);
                basketNameParagraph.setFixedPosition(830, 520, 500);
                setBorderForAllElement(basketNameParagraph);
                basketNameParagraph.setHeight(90);
                basketNameParagraph.setWidth(500);
                setBorderForAllElement(basketNameParagraph);

                canvasPage2.setStrokeColor(dZloty);
                canvasPage2.moveTo(830, 510);
                canvasPage2.lineTo(1300, 510).setLineWidth(3f);
                canvasPage2.closePathStroke();

                 Set<Product> productsList = basket.getProducts();


                int hPosition = 460;
                for (Product products : productsList) {

                    Text productName = new Text("- " + products.getProductName() + " " + products.getProductCapacity())

                        .setFont(fontRobotoItalic)
                        .setFontSize(18)
                        .setFontColor(dSzary)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setHorizontalAlignment(HorizontalAlignment.CENTER);



                    if(currentPosition!=0){

                        if(products.getProductName().length() > 52){
                            hPosition -=  38;
                        }else{
                            hPosition -= 20;
                        }
                    }



                    Paragraph productNameParagraph = new Paragraph().add(productName);
                    productNameParagraph.setFixedPosition(830, hPosition, 500);


                    if(products.getProductName().length() > 52){
                        productNameParagraph.setHeight(60);
                        productNameParagraph.setFixedLeading(20f);
                    }else{
                        productNameParagraph.setHeight(50);
                    }

                    //setBorderForAllElement(productNameParagraph);
                    //productNameParagraph.setHeight(50);
                    productNameParagraph.setWidth(500);
                    //setBorderForAllElement(productNameParagraph);




                    doc.add(productNameParagraph);
                    currentPosition++;



                        if(products.getProductName().length() > 52) {
                            hPosition -= 7;
                        }
                }

                canvasPage2.setStrokeColor(dZloty);
                canvasPage2.moveTo(830, hPosition - 1);
                canvasPage2.lineTo(1300, hPosition - 1).setLineWidth(3f);
                canvasPage2.closePathStroke();

                BigDecimal db = new BigDecimal(basket.getBasketTotalPrice()).divide(new BigDecimal("100"));

                StringBuilder convertedPriceb = new StringBuilder();
                convertedPriceb
                    .append(db.toString())
                    .append(" zł");

                String convertedPrice = convertedPriceb.toString();



                Text basketPrice = new Text(convertedPrice)


                    .setFont(fontTheme)
                    .setFontSize(40)
                    .setFontColor(dBordowy)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setHorizontalAlignment(HorizontalAlignment.CENTER);

                Paragraph basketPriceParagraph = new Paragraph().add(basketPrice);






                switch (convertedPrice.length()) {
                    case 5:
                        log.error("CEEEEEEEEE" + convertedPrice.length());
                        basketPriceParagraph.setFixedPosition(1180, hPosition - 90, 500);
                        break;
                    case 6:
                        basketPriceParagraph.setFixedPosition(1150, hPosition - 90, 500);
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        break;
                    case 7:
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        basketPriceParagraph.setFixedPosition(1120, hPosition - 90, 500);
                        break;
                    case 8:
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        basketPriceParagraph.setFixedPosition(1110, hPosition - 90, 500);
                        break;
                    case 9:
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        basketPriceParagraph.setFixedPosition(1080, hPosition - 90, 500);
                        break;
                    case 10:
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        basketPriceParagraph.setFixedPosition(1050, hPosition - 90, 500);
                        break;
                    default:
                        log.error("CEEEEEEEEE" + convertedPrice.length());

                        basketPriceParagraph.setFixedPosition(1160, hPosition - 90, 500);
                        break;

                }





                setBorderForAllElement(basketPriceParagraph);
                basketPriceParagraph.setHeight(90);
                basketPriceParagraph.setWidth(500);
                setBorderForAllElement(basketPriceParagraph);


                doc.add(topImage);
                doc.add(catalogNameContentParagraph);
                doc.add(BasketImage);
                doc.add(basketNameParagraph);
                doc.add(basketPriceParagraph);


            }

  //////////////////////////////////////////////////////////////////////////////////////
        // PdfCanvas canvasPage3 = new PdfCanvas(pdfDoc.addNewPage());
        doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));


        Image imgLast = new Image(ImageDataFactory.create(FIRST_PAGE_IMAGE));
        imgLast.setFixedPosition(475, 0);
        imgLast.setHeight(768);
        imgLast.setWidth(891);




        if (catalog.getLogo() != null){
            Image logoImgLast = new Image(ImageDataFactory.create(catalog.getLogo()));
            logoImgLast.setFixedPosition(40, 450);
            logoImgLast.setHeight(300);
            logoImgLast.setWidth(400);
            doc.add(logoImgLast);
        }




        Text catalogAdditionalDes = new Text("");

        if (catalog.getCatalogAdditionalDesc() == null) {

            catalogAdditionalDes = new Text(" ");
        }else{
            catalogAdditionalDes = new Text(catalog.getCatalogAdditionalDesc());

        }


        catalogAdditionalDes
            .setFont(fontRobotoRegular)
            .setFontSize(17)
            .setFontColor(dSzary)
            .setTextAlignment(TextAlignment.CENTER)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

        Paragraph lastPageParagraph = new Paragraph().add(catalogAdditionalDes);
        lastPageParagraph.setFixedPosition(30, 40, 350);
        setBorderForAllElement(lastPageParagraph);
        lastPageParagraph.setHeight(380);
        lastPageParagraph.setWidth(408);
        lastPageParagraph.setFixedLeading(20f);

        doc.add(imgLast);
        doc.add(lastPageParagraph);

        doc.close();

        return new ByteArrayInputStream(out.toByteArray());
    }

    private static Paragraph setBorderForAllElement (Paragraph paragraph){

        //return   paragraph.setBorder(new SolidBorder(1));
        return   paragraph.setBorder(Border.NO_BORDER);
    }

    private static Image setBorderForAllElement (Image image){
        //return   image.setBorder(new SolidBorder(1));
        return   image.setBorder(Border.NO_BORDER);
    }
}
