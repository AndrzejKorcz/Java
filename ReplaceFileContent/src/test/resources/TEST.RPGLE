       //  @ObjectNameT program testowy
       //---------------------------------------------------------------
       //  Project ID   Pgmr   Date        Project Description
       //  -----------  ----  ----------   -----------------------------
       //  @ProjectId      xxx   @CurrtDate   nowy obiekt
       //---------------------------------------------------------------
       ctl-opt main(Main) debug(*yes) option(*srcstmt:*nodebugio)
               bnddir('TSTBIND') ;

       /define @FunctionName
       /copy @Source,@ObjectNameP
       /undefine @FunctionName

       dcl-proc Main ;
        dcl-pi *n extpgm('@ObjectNameT') ;
        end-pi ;
        dcl-ds request likeds(parmIn_t) inz ;
        dcl-ds response likeds(parmOut_t) inz ;
        dcl-ds error likeds(errDs_t) inz ;

        dcl-s httpStatus int(10) inz ;
        dcl-s httpHeaders char(100) dim(10) inz ;

        @FunctionName(request
                     :response
                     :error
                     :httpStatus
                     :httpHeaders) ;

        dsply httpStatus ;

        if httpStatus = H_OK ;
          dsply 'succes' ;
        endif ;

        return;

       end-proc;
