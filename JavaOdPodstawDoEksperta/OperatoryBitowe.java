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
public class OperatoryBitowe {
     public static void main(String[] args) {
        /*
           
            0 - false 1 - true
            OPERATORY BITOWE
            & - iloczyn bitowy
            | - suma bitowa
            ^ - XOR eXclusive OR (ALBO)
            x << 1 - przesunięce w lewo o 1
            x >> 2 - przesunięcie w prawo o 2
        
            ~negacja bitowa
        
            1 2 6 = 1 * 10 ^ 2 + 2 * 10 ^ 1 + 6 * 10 ^ 0
        
            1 0 1 0 = 2 ^ 3 + 2 ^ 1 = 8 + 2 = 10
            
        
            1 1 - 0
            0 0 - 0
            1 0 - 1
            0 1 - 1
            
            1 0 1 0 // 10        
            1 0 1 1 // 11
        
            --------
            0 0 0 1 // 1
        
        
            1 0 1 0
            0 1 0 1
        
        
            0 0 0 1 - posta na forum
            0 0 1 0 - edytuj na forum
            0 1 0 0 - usun posta
            1 0 0 0 - ban
        
            1 1 1 1 // 15
        
            
            00 0 00 0 0 0 00    0 1 0 
                                0 2 0  = 2 
            
        */
            
            System.out.println(~10);
    }
 
}
