/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.view.model;

import hr.algebra.model.Image;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dnlbe
 */
public class ImageTableModel extends AbstractTableModel{
    
    private static final String[] COLUMN_NAMES = {"Id",
        "Title",
        "Link",
        "Description",
        "Picture path",
        "Published date"
    };
    
    private List<Image> images;

    public ImageTableModel(List<Image> images) {
        this.images = images;
    }

    public void setFilms(List<Image> images) {
        this.images = images;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return images.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return images.get(rowIndex).getId();
            case 1:
                return images.get(rowIndex).getTitle();
            case 2:
                return images.get(rowIndex).getLink();
            case 3:
                return images.get(rowIndex).getDescription();
            case 4:
                return images.get(rowIndex).getPicturePath();
            case 5:
                return images.get(rowIndex).getPublishedDate().format(Image.DATE_FORMATTER);
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
