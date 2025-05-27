/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Writer;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dnlbe
 */
public class WriterTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {
        "Id",
        "First Name",
        "Last Name"
    };
     
    private List<Writer> writers;
    

    public WriterTableModel(List<Writer> writers) {
        this.writers = writers;
    }
    
    public void setWriters(List<Writer> writers) {
        this.writers = writers;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return writers.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return writers.get(rowIndex).getId();
            case 1:
                return writers.get(rowIndex).getFirstName();
            case 2:
                return writers.get(rowIndex).getLastName();
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
