/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JavaOdPodstawDoEksperta;

/**
 *
 * @author 160246
 */
public class PetlaDoWhile {
    public static void main(String[] args) {
        String[] kursyProgramowania = 
        {
            "Java", 
            "Java Aplikacje", 
            "Java Strumienie",
            "Java Aspekty Zaawansowane", 
            "Java Android",
            "C#", 
            "C# Tworzenie Aplikacji",
            "C# LINQ",
            "Pascal",
            "AutoIT"
        };
        // for (INICJALIZACJA ZMIENNYCH; WARUNEK PETLI; CO MA SIE STAC PO WYKONANIU INSTRUKCJI WSZYSTKICH W PETLI)
//        for(int i = 0; i < kursyProgramowania.length; i++)
//        {           
//            System.out.println(kursyProgramowania[i]);           
//           
//        }
     
        //ENHANCED FOR
      
        for(String nazwaKursu: kursyProgramowania)
        {
            System.out.println(nazwaKursu);
        }
        
        
      /*  
        i = 1;
        do
        {
           System.out.println(kursyProgramowania[i]);
            
            i++; 
       }while(i < kursyProgramowania.length);
              */
        
    }
   
}
