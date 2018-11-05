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
public class PetlaBreakContinue {
   public static void main(String[] args) {
        /*
            1 2 3 4 5 6 7 8 9 10
        
            2 4 6 8 10 12 14 16 18 20
        
        */
        
        for (int j = 1; j <= 10; j++) // j =2
        {
            for (int i = 1; i <= 10; i++) // i = 5
            {
                
                System.out.print(i * j + " ");
            }
            System.out.println();
        }
//        
//        for (int i = 0; i < 60; i++)
//        {
//            if (i % 2 != 0)
//                continue; //przerwij iteracje od tego momentu i nie wykonuj już żadnych instrukcji po mnie
//                
//            System.out.println(i);
//        }
        
    }   
}
