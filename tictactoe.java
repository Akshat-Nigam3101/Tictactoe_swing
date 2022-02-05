package gd;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class tictactoe extends JFrame implements ActionListener
{
    public static int BOARD_SIZE=3;
    public static enum GameStatus{
        Incomplete, XWins,ZWins,Tie
    }
    private JButton[][] buttons = new JButton[BOARD_SIZE][BOARD_SIZE];
    boolean crossTurn=true;
    public tictactoe()
    {
        super.setTitle("TicTacToe");
        super.setSize(800,800);
        GridLayout grid=new GridLayout(BOARD_SIZE,BOARD_SIZE);
        super.setLayout(grid);
        Font font=new Font("comic sans",1, 150);
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                JButton button =new JButton("");
                buttons[i][j]=button;
                button.setFont(font);
                button.addActionListener(this);
                super.add(button);
            }
        }
        super.setResizable(false);
        super.setVisible(true);
        
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        JButton clickedButton =(JButton)e.getSource();
        makeMove(clickedButton);
        GameStatus gs=this.getGameStatus();
        if(gs==GameStatus.Incomplete)
        {
            return;
        }
        declareWinner(gs);
        int choice =JOptionPane.showConfirmDialog(this,"do you want to restart?");
        if(choice==JOptionPane.YES_OPTION)
        {
         for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
              buttons[i][j].setText("");
            }
        }   
        crossTurn=true;
        }
        else
        {
            super.dispose();
        }
    }
    private void makeMove(JButton clickedButton)
    {
            String btntxt=clickedButton.getText();
            if(btntxt.length()>0)
            {
                JOptionPane.showMessageDialog(this,"Invalid Move");
                
            }else
            {
                if(crossTurn)
                 {
                     clickedButton.setText("X");
                 }
                 else
                 {
                     clickedButton.setText("0");
                 }
                 crossTurn=!crossTurn;
            }
    }
    private GameStatus getGameStatus()
    {
        String txt1="",txt2="";
        int row=0,col=0;    
        while(row<BOARD_SIZE)
        {
            col=0;
            while(col<BOARD_SIZE-1)
            {
                txt1=buttons[row][col].getText();
                txt2=buttons[row][col+1].getText();
                if(!txt1.equals(txt2)||txt1.length()==0)
                {
                   break; 
                }
                col++;
            }
            if(col==BOARD_SIZE-1)
            
            {
                if(txt1.equals("X"))
                {
                    return GameStatus.XWins;
                    
                }
                else
                {
                    return GameStatus.ZWins;    
                }
            }
            row++;
        }
        col=0;
            while(col<BOARD_SIZE)
        {
            row=0;
            while(row<BOARD_SIZE-1)
            {
                txt1=buttons[row][col].getText();
                txt2=buttons[row+1][col].getText();
                if(!txt1.equals(txt2)||txt1.length()==0)
                {
                   break; 
                }
                row++;
            }
            if(row==BOARD_SIZE-1)
            
            {
                if(txt1.equals("X"))
                {
                    return GameStatus.XWins;
                    
                }
                else
                {
                    return GameStatus.ZWins;    
                }
            }
            col++;
        }
        //d1
        row=0;
        col=0;
        while(row<BOARD_SIZE-1)
        {
            txt1=buttons[row][col].getText();
                txt2=buttons[row+1][col+1].getText();
                if(!txt1.equals(txt2)||txt1.length()==0)
                {
                   break; 
                }
                row++;
                col++;
                
        }
        if(row==BOARD_SIZE-1)
        {
                if(txt1.equals("X"))
                {
                    return GameStatus.XWins;
                    
                }
                else
                {
                    return GameStatus.ZWins;    
                }
        }
        //d2
        row=BOARD_SIZE-1    ;
        col=0;
        while(row>0)
        {
            txt1=buttons[row][col].getText();
                txt2=buttons[row-1][col+1].getText();
                if(!txt1.equals(txt2)||txt1.length()==0)
                {
                   break; 
                }
                row--;
                col++;
                
        }
        if(row==0)
        {
                if(txt1.equals("X"))
                {
                    return GameStatus.XWins;
                    
                }
                else
                {
                    return GameStatus.ZWins;    
                }
        }
        String txt="";
        for( row=0;row<BOARD_SIZE;row++)
        {
            for( col=0;col<BOARD_SIZE;col++)
            {
                txt=buttons[row][col].getText();
            }
            if (txt.length()==0)
            {
                return GameStatus.Incomplete;
            }
    }
    return GameStatus.Tie;
    
}
private void declareWinner(GameStatus gs)
{
    if(gs==GameStatus.XWins)
    {
         JOptionPane.showMessageDialog(this,"X wins");
    }
    else if(gs==GameStatus.ZWins)
    {
         JOptionPane.showMessageDialog(this,"0 wins");
    }
    else
    {
         JOptionPane.showMessageDialog(this,"its a tie!");
    }
    
}

}

