package com.example.niva.oophw3;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class PuzzleActivity extends AppCompatActivity {

    private TextView [] tilesArray;
    private TableRow[] rows;
    private TableLayout myLayout;
    private GameBoard board;
    private Button cmdNewGame;
    private TableRow.LayoutParams lp;
    private final static int NUM_OF_TILES = 16;
    private final static int NUM_OF_ROWS = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle);

        board = new GameBoard();
        cmdNewGame = (Button)findViewById(R.id.cmdGenerate);
        cmdNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGame();
            }
        });
        myLayout = (TableLayout)findViewById(R.id.layout);
        lp = new TableRow.LayoutParams( 0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
        lp.setMargins(5, 5, 5, 5);
//        lp.gravity= Gravity.CENTER;
        tilesArray = new TextView[NUM_OF_TILES];
        rows = new TableRow[NUM_OF_ROWS];
        drawBoard();
    }

    private void newGame() {
        board = new GameBoard();
        repaint();
    }

    private void drawBoard(){
        int tileNum = 0;
        for (int i = 0; i < NUM_OF_ROWS ; i++) {
            rows[i] = new TableRow(this);
            for (int j = 0; j < NUM_OF_ROWS; j++, tileNum++) {

                tilesArray[tileNum] = new TextView(this);
                tilesArray[tileNum].setTextSize(50);
                tilesArray[tileNum].setLayoutParams(lp);
                tilesArray[tileNum].setId(tileNum);
                tilesArray[tileNum].setBackgroundColor(Color.BLUE);

                tilesArray[tileNum].setBackground(getDrawable(R.drawable.square));
                int val = board.getTile(i, j);
                tilesArray[tileNum].setText((val) + "");
                if(tilesArray[tileNum].getText().equals(NUM_OF_TILES+""))
                    tilesArray[tileNum].setVisibility(View.INVISIBLE);

                tilesArray[tileNum].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv = (TextView)v;
                        int num = Integer.parseInt(tv.getText().toString());
                        boolean b = board.moveTile(num);
                        repaint();
                    }
                });
                rows[i].addView(tilesArray[tileNum]);
            }
            myLayout.addView(rows[i]);
        }

    }

    private void repaint(){
        int k = 0;
        for (int i = 0; i < NUM_OF_ROWS ; i++) {
            for (int j = 0; j < NUM_OF_ROWS ; j++) {
                int val = board.getTile(i, j);
                tilesArray[k].setText((val) + "");
                if (tilesArray[k].getText().toString().equals(NUM_OF_TILES + ""))
                    tilesArray[k].setVisibility(View.INVISIBLE);
                else
                    tilesArray[k].setVisibility(View.VISIBLE);
                k++;
            }
        }

        if(board.isOrdered()){
            Toast.makeText(this, "Well Done!", Toast.LENGTH_LONG).show();
            for (int i = 0; i < tilesArray.length; i++) {
                tilesArray[i].setOnClickListener(null);
            }
        }
    }
}
