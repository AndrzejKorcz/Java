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
public class PetlaFor {
   public static void main(String[] args) {
        int[] liczby = {1, 2, 3, 4, 5, 6};
        
        int suma = 0;
        
        for (int liczba: liczby)
        {
            suma += liczba;
        }
        
        System.out.println(suma);
        
        
    }   
}
