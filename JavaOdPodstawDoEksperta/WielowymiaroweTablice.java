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
public class WielowymiaroweTablice {
 public static void main(String[] args) {
        /*
        
            ------------------------------------
               [0][0]   |  [0][1] |   [0][2]   |
            ------------------------------------
            ------------------------------------
               [1][0]   |  [1][1] |   [1][2]   |
            ------------------------------------
            ------------------------------------
               [2][0]   |  [2][1] |   [2][2]   |
            ------------------------------------
            ------------------------------------
               [3][0]   |  [3][1] |   [3][2]   |
            ------------------------------------       
            
        
        */
     
        int[][] tab = new int[4][3];
        
        tab[0][0] = 25;
        tab[3][2] = 70;
        
        System.out.println(tab[3][2]);
        
        int[][] tab2 = 
        {
            {4, 15, 17},
            {5, 12, 125},
            {651, 1256, -24},
            {6555, 2, -2444}
        };
        
        System.out.println(tab2[3][0]);        
        
    }
    
   
}
