       //------------------------------------------------------------------
       //  @ObjectNameM  modul uslugi
       //  ************************* Change Log ***************************
       //  Project ID   Pgmr   Date       Project Description
       //  -----------  ----  -------     ---------------------------------
       //  @ProjectId      xxx   @CurrtDate  nowy obiekt
       //------------------------------------------------------------------

       ctl-opt nomain debug(*yes) PGMINFO(*PCML:*MODULE:*DCLCASE)
	           bnddir('RSTBIND': 'DEVBIND' : 'AIABIND') ;

       /define @FunctionName
       /copy @Source,@ObjectNameP
       /undefine @FunctionName

       /define logger
       /copy aiasorc,AIALOG01P
       /undefine logger

       /define PrepareErrorStructure
       /copy RSTSORC,RSTSF1001P
       /undefine PrepareErrorStructure


       //-----------------------------------------------------------------
       // Prepare Response Record
       //-----------------------------------------------------------------
       dcl-proc prepareResponseRec;
         dcl-pi *n;
           responseRec likeds(record_t);
         end-pi;

          dsply 'hello from prepareResponseRec()' ;
          return ;
       end-proc;

       //-----------------------------------------------------------------
       // @FunctionName
       //-----------------------------------------------------------------
       dcl-proc @FunctionName export ;
         dcl-pi *n ;
           Request likeds(parmIn_t) const ;
           Response likeds(parmOut_t) ;
           Error likeds(errDs_t) ;
           HttpStatus int(10) ;
           HttpHeaders char(100) dim(10) ;
         end-pi ;

         clear httpHeaders ;
         clear Response ;

         initLogger('@FunctionName');

         httpStatus = H_OK;

         prepareResponseRec();

         return ;

       on-exit;
         endLogger(library) ;

       end-proc ;

