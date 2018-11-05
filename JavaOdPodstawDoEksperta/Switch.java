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
public class Switch {
     public static void main(String[] args) {
        /*
            Switch (przełącznik)
        */
        char a = 50;
        switch(a)
        {
            case 50:
                System.out.println("a jest równe 50");
                break;
            case 100:
                System.out.println("a jest równe 100");    
                break;
            default:
                System.out.println("a nie jest równe ani 50 ani 100 jest równe " + a);
        }
    }   
}
