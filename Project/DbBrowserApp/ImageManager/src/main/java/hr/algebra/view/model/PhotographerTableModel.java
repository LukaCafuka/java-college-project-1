/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Photographer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dnlbe
 */
public class PhotographerTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {
        "Id",
        "First Name",
        "Last Name"
    };
     
    private List<Photographer> photographers;
    

    public PhotographerTableModel(List<Photographer> photographers) {
        this.photographers = photographers;
    }
    
    public void setPhotographers(List<Photographer> photographers) {
        this.photographers = photographers;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return photographers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return photographers.get(rowIndex).getId();
            case 1:
                return photographers.get(rowIndex).getFirstName();
            case 2:
                return photographers.get(rowIndex).getLastName();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        
        return super.getColumnClass(columnIndex); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
