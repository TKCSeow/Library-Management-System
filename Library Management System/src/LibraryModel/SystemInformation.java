/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryModel;

import java.time.LocalDate;

/**
 *
 * @author tseow
 */
public class SystemInformation {
    
    private LocalDate currentDate;
    
    public SystemInformation()
    {
        this.currentDate = LocalDate.now();
    }
    
}
