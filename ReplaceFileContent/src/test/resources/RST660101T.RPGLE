       //  RST660101T program testowy
       //---------------------------------------------------------------
       //  Project ID   Pgmr   Date        Project Description
       //  -----------  ----  ----------   -----------------------------
       //  NP10666      xxx   2019/12/17   nowy obiekt
       //---------------------------------------------------------------
       ctl-opt main(Main) debug(*yes) option(*srcstmt:*nodebugio)
               bnddir('TSTBIND') ;

       /define getFunction
       /copy RSTSORC,RST660101P
       /undefine getFunction

       dcl-proc Main ;
        dcl-pi *n extpgm('RST660101T') ;
        end-pi ;
        dcl-ds request likeds(parmIn_t) inz ;
        dcl-ds response likeds(parmOut_t) inz ;
        dcl-ds error likeds(errDs_t) inz ;

        dcl-s httpStatus int(10) inz ;
        dcl-s httpHeaders char(100) dim(10) inz ;

        getFunction(request
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