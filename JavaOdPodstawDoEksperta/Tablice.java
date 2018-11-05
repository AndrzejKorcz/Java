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
public class Tablice {

    public static void main(String[] args) {
     
        /*
        
            -----------------------------------
             tab[0] tab[1] tab[2] tab[3] tab[4]
        
            ----------------------------------
        
        */
        
        int[] tab = new int[5];
        
       // tab = new int[5];
        
        tab[0] = 20;
        tab[1] = 7;
        tab[2] = 27;
        tab[3] = 15;
        tab[4] = 77;        
        
        System.out.println(tab[0]);
        
        int[] tab2 = {4, 14, 4, 412, 1254, 512};
        
        System.out.println(tab2[tab2.length-1]);
    }    
}
