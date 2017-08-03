package com.sapper.telnov.sapperandroidapp.customView.fieldPanel;

import game.Cell;

/**
 * Created by Sergey on 31.07.2017.
 */

public interface ICellListener {
    void openCell(Cell c);
    void putTagged(Cell c);
}
