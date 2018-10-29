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
public class OperatoryLogiczne {
    
     public static void main(String[] args) {
        /*
            Operatory Logiczne 
        
            true false
        
            ! - negacji
        
            !(true) - false
            !(false) - true
        
            && - koniunkcja - "I" - W TYM SAMYM MOMENCIE
        
            true true - true
            true false - false
            false true - false
            false false - false
        
            KONIUNKCJA JEST PRAWDZIWA WTEDY I TYLKO WTEDY GDY OBA WYRAZENIA SA PRAWDZIWE
        
            || - alternatywa - "lub" - to lub też to
        
            true true - true
            true false - true
            false true - true
            false false - false
            
            ALTERNATYWA JEST FAŁSZYWA TYLKO GDY  OBA WYRAZENIA SA FALSZYWE
        
        */
        
        int a = 5,
            b = 5,
            c = 7;
       
        if (a != b || c > 12)        
            System.out.println("tak");
        
    }  
}
