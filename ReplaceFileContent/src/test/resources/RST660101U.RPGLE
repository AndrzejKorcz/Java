       //---------------------------------------------------------------
       //  RST660101U  testy jednostkowe
       //---------------------------------------------------------------
       //  Project ID   Pgmr   Date        Project Description
       //  -----------  ----  ----------   -----------------------------
       //  NP10666      xxx   2019/12/17   nowy obiekt
       //---------------------------------------------------------------
       ctl-opt option(*srcstmt:*nodebugio) nomain debug(*yes)
               bnddir('TSTBIND': 'RSTBIND') ;

       /copy rpgunit1,testcase
       /copy rpgunity1,templates

       /define helper
       /define library
       /define files
       /copy RSTSORC,RSTUT1001P
       /undefine library
       /undefine files
       /undefine helper

       /define getFunction
       /copy RSTSORC,RST660101P
       /undefine getFunction

       dcl-proc setUpSuite export;
       end-proc;

       dcl-proc tearDownSuite export;
       end-proc;

       //-- Test Case Definitions
       dcl-proc test_getFunction_OK export ;
         // Arrange:
         dcl-ds request likeds(parmIn_t) inz ;
         dcl-ds response likeds(parmOut_t) inz ;
         dcl-ds error likeds(errDs_t) inz ;

         dcl-s httpStatus int(10) inz ;
         dcl-s httpHeaders char(100) dim(10) inz ;

         dcl-s expected int(10) inz(H_OK) ;
         // Act:
         getFunction(request
                      :response
                      :error
                      :httpStatus
                      :httpHeaders) ;

         // Assert:
         assert(expected = httpStatus : 'Expected 200 ');

       end-proc ;