/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetofinal;

import GUI.Menu;
import GUI.SplashScreen;

/**
 *
 * @author Vinicius
 */
public class ProjetoFinal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        SplashScreen splash = new SplashScreen(5000);
        splash.showSplashAndExit();
        Menu m = new Menu();
        m.setVisible(true);
        
        
        
        
        
        
    }
    
}
