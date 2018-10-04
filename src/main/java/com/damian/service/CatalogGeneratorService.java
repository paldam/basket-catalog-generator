package com.damian.service;

import com.damian.domain.CatalogArchive;
import com.damian.domain.User;
import com.damian.repository.CatalogArchiveRepository;
import com.damian.repository.UserRepository;
import com.damian.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.Instant;
import java.util.Optional;


@Service
public class CatalogGeneratorService {

    private static final Logger log = LoggerFactory.getLogger(CatalogGeneratorService.class);

    private CatalogArchiveRepository catalogArchiveRepository;
    private UserRepository userRepository;

    public CatalogGeneratorService(CatalogArchiveRepository catalogArchiveRepository, UserRepository userRepository) {

         this.catalogArchiveRepository = catalogArchiveRepository;
         this.userRepository = userRepository;

    }



     public void generateCatalog(CatalogArchive catalog) {

         catalog.setUser(this.getUser());
         catalog.setDateOfGenerate(Date.from(Instant.now()));
         CatalogArchive result = catalogArchiveRepository.save(catalog);


     }

     private User getUser(){

         Optional<String> userLogin = SecurityUtils.getCurrentUserLogin() ;

         if (userLogin.isPresent() ){
              Optional<User> userTmp = userRepository.findOneByLogin(userLogin.get());

              return userTmp.get();


         }  else {
            return null;
         }

     }



}
