           //----------------------------------------------------------------
           //  @ObjectNameP  prototyp uslugi
           //  --------------------------------------------------------------
           //  Project ID   Pgmr   Date       Project Description
           //  -----------  ----  -------     -------------------------------
           //  @ProjectId      xxx   @CurrtDate  nowy obiekt
           //----------------------------------------------------------------
           // Procedury exportowane:
           // - @FunctionName - @description
           //----------------------------------------------------------------

           /copy aiasorc,HTTPHDRMSG

           /if defined (@FunctionName)
           //*** @FunctionName - @description
           //*
           //* Parametry:
           //* Request     - dane wejsciowe opisane struktura parmIn_t  (I)
           //* Response    - dane wyjsciowe opisane struktura parmOut_t (O)
           //* Error       - struktura bledu opisana struktura errDs_t  (O)
           //* HttpStatus  - kod odpowiedzi usslugi (HTTP) np 200 - ok  (O)
           //* HttpHeaders - ewentualne headery dla uslug http          (O)
           //**
            dcl-pr @FunctionName ;
              Request likeds(parmIn_t) const ;
              Response likeds(parmOut_t) ;
              Error likeds(errDs_t) ;
              HttpStatus int(10) ;
              HttpHeaders char(100) dim(10) ;
            end-pr ;

            // maksymalny rozmiar strony
            dcl-c maxRecordsLength const(1000);

            //***
            //* dane wejsciowe
            dcl-ds parmIn_t qualified template ;
            end-ds ;
            //**

            //***
            //* dane wyjsciowe
            dcl-ds parmOut_t qualified template ;
              records_LENGTH int(10) ;
              records likeds(record_t) dim(maxRecordsLength) ;
            end-ds ;
            //**

            //***
            //* struktura rekordu danych
            dcl-ds record_t qualified template ;
            end-ds;
            //**

            dcl-ds errDs_t extname('ANFB00102') qualified template ;
            end-ds ;

          /endif
