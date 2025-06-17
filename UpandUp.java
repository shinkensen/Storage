import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class UpandUp extends Drawing {

    public static void main(String[] args) {
        Runner.project = new UpandUp();
        Runner.project.drawGrid = drawGrid;
        Runner.project.directions = directions;
        Runner.project.windowTitle = windowTitle;
        Runner.main();
    }
    //Change this to false to turn off the grid
    static boolean drawGrid = false;
    //Change the strings in the the array to display your directions for your project
    static String[] directions = {"WAD or arrow keys to jump and move.","Every time you touch the celing, it will show the next floor","Try to get up as fast as possible","Good luck!, <30 secs?- Name on title screen"};
    static String windowTitle = "Up&Up";
    //Global Variables go herea
    
    int[] dp={75,100,10,30}, vel=new int[]{0,0};
    //int[] dp2={400,100,20,20}, vel2=new int[]{0,0};
    int[][][]barriers={{{600,300,100,50},{200,400,100,50}},
    {{500,250,100,50},{200,400,100,50}},
    {{400,200,60,10},{300,350,50,50}},
    {{200,250,100,50},{550,440,20,20}},
    {{201,200,60,90},{20,470,40,20}},
    {{400,200,60,10},{300,350,50,50}},
    {{200,250,100,50},{550,440,20,20}},
    {{500,250,100,50},{200,400,100,50}},
    {{400,200,60,10},{300,350,50,50}},
    {{200,250,100,50},{550,440,20,20}},
    {{201,200,60,90},{20,470,40,20}},
    {{400,200,60,10},{300,350,50,50}},
    {{200,250,100,50},{550,440,20,20}},
    {{201,200,60,90},{20,470,40,20}},
    
    {{300,300,30,60},{200,450,40,10}}};
    //int[][] col=new int [][]{{200, 200, 20, 100},{350, 300, 30, 120},{520, 150, 20, 180},{150, 200, 20, 70}};
    int alt=0,maxAlt=0,maxAlt2=0;
    int jumps=0,jumps2=0;
    int lvl=0;
    Font font = new Font("Times New Roman", Font.BOLD, 50);
    Font font2 = new Font("Consolas", Font.BOLD, 20);
    boolean u1=true,onGround=false,onGround2=false,u4=true,u5=true,u6=true,u7=false,u8=true,u9=true;
    int stage=0;
    int timing=0,globalTiming=0,minT=0,graVel=-10,y=50;
    int[] text= {140,y,100,50,5,0};
    int r=101,g=150,b=250,r2=1,g2=1,b2=1,attempt=1;
    

    
    
    @Override
    public void drawPerFrame(Graphics2D g2d)  
    { 
        Color home= new Color(r,g,b);
        if (u7==true){
            timing++;
        }
        
        
        globalTiming++;
        alt=(600*lvl)+(600-(dp[1]+(dp[3]/2)));
        r+=r2;
        g+=g2;
        b+=b2;
        if (r>254||r<=100){
            r2*=-1;
        }
        if (g>254||g<=100){
            g2*=-1;
        }
        if (b>254||b<=100){
            b2*=-1;
        }
        if (alt>maxAlt){
            maxAlt=alt;
        }
        if (alt>maxAlt2){
            maxAlt2=alt;
        }
        String t="Altitude: "+alt;
        String t2="Floor: "+lvl;
        String t3="Tries: "+attempt;
        String t6="Time: "+timing/60;
        
        g2d.setColor(Color.ORANGE);
        g2d.setFont(font2);
        g2d.drawString(t, 600, 20);
        g2d.drawString(t2, 600, 40);
        g2d.drawString(t3, 600, 60);
        g2d.drawString(t6, 600, 80);
        if (stage==0){
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,800,600);
            g2d.setColor(Color.GREEN);
            g2d.fillRect(330,480,150,60);
            if(Mouse.x<480&&Mouse.x>330&&Mouse.y<540&&Mouse.y>480&&Mouse.button||Keys.s){
                stage=1;
            }
            g2d.setColor(Color.WHITE);
            g2d.setFont(font);
            g2d.drawString("Start", 350, 530);
            direction(g2d);
            g2d.setColor(home);
            g2d.setFont(font);
            g2d.drawString("Up & Up", 400, 50);
            if (globalTiming<270){
                g2d.setFont(font);
                g2d.drawString("Welcome to ",140, 50);
            }
            else{
                g2d.setFont(font);
                g2d.drawString("Welcome to ",text[0], text[1]);
                if (!u6){
                    text[1]+=graVel;
                    graVel+=2;
                }
                //if (text[1]>600){
                //    u6=true;
                //}
            }
        }
        
        if (stage==1){
            u7=true;
            px();
            g();
            //px2();
            //g2();
            bars();
            drawBars(g2d);
            sides();
            
            draw(g2d);
            //g2d.setColor(Color.CYAN);
            //g2d.fillOval(dp2[0], dp2[1], 20, 20);
            
            levels(g2d);
            //wall 1
            win();
            onGround=false;
        }
        if (stage==2){
            
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,800,600);
            g2d.setColor(home);
            g2d.setFont(font);
            g2d.drawString("You Died. Press R to reset", 100, 300);
            String t4="Max(all runs) altitude: "+maxAlt;
            String t5="Max altitude: "+maxAlt2;
            g2d.setColor(Color.ORANGE);
            g2d.setFont(font2);
            g2d.drawString(t4, 250, 350);
            g2d.drawString(t5, 300, 370);
            if (Keys.r){
                reset();
            }
        }
        if (stage==3){
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0,0,800,600);
            g2d.setColor(home);
            g2d.setFont(font);
            
            if (timing<minT){
                minT=timing;
            }
            String t7="Best: "+minT/60;
            g2d.drawString("You won congrats! Press r", 50, 300);
            String t4="Max(all runs) altitude: "+maxAlt;
            String t5="Max altitude: "+maxAlt2;
            g2d.setColor(Color.ORANGE);
            g2d.setFont(font2);
            g2d.drawString(t6, 250, 350);
            
            g2d.drawString(t7, 250, 370);
            g2d.drawString(t5, 250, 390);
            if (Keys.r){
                reset();
            }
        }
    }
    public void direction(Graphics2D g2d){
        g2d.setColor(Color.ORANGE);
        g2d.setFont(font2);
        String[] strings= {"Press WAD, Arrows, or space bar to move and jump", "When you reach the ceiling, it will show the next floor", 
            "When you touch bottom, it will show the floor beneath", "When you touch the ground you will die."};
        int string=300;
        for (int i=0;i<strings.length;i++){
            g2d.drawString(strings[i],100,string);
            string+=20;
        }
    }
    public void win(){
        if (lvl==barriers.length-1&&dp[1]<100){
            stage=3;
            u7=false;
        }
    }
    public void texturer(){
        
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.MAGENTA);
        g2d.fillOval(dp[0], dp[1], dp[2], dp[3]);
    }
    public void reset(){
        lvl=0;
        jumps=0;
        timing=0;globalTiming=0;graVel=-10;y=50;
        u1=true;onGround=false;onGround2=false;u4=true;u5=true;u6=true;u7=false;u8=false;
        dp[0]=75;
        dp[1]=100;
        dp[2]=10;
        dp[3]=30;
        text[0]=140;
        text[1]=y;
        text[2]=100;
        text[3]=50;
        text[4]=5;
        text[5]=0;
        stage=0;
        maxAlt2=0;
        timing=0;
    }
    //Methods go HERE
    //Player 1
    public void drawBars(Graphics g2d){
        for (int i=0;i<barriers[lvl].length;i++){
            g2d.setColor(Color.GREEN);
            g2d.fillRect(barriers[lvl][i][0], barriers[lvl][i][1], barriers[lvl][i][2], barriers[lvl][i][3]);
        }
    }
    public void sides(){
        if (dp[0]<0){
            dp[0]=0;
        }
        if (dp[0]+dp[2]>780){
            dp[0]=780-dp[2];
        }
    }
    public void levels(Graphics2D g2d){
    
        if (barriers.length>lvl){
            if (dp[1]<5){
                lvl+=1;
                dp[1]=500;
                u8=true;
            }
        }
        else{
            stage++;
        }
        
    }
    public void px(){
        
        if (Keys.d||Keys.right){
            vel[0]=5;
        }
        else if (Keys.a||Keys.left){
            vel[0]=-5;
        }
        else{
            vel[0]=0;
        }
        dp[0]+=vel[0];
    }
    public void bounce(){
        if (text[0]<=0){
            text[4]=5;
        }
        if (text[0]+250>=800){
            text[4]=-5;
        }
        if (text[1]-20<=0){
            text[5]=5;
        }
        if (text[1]>=600){
            text[5]=-5;
        }
        text[0]+=text[4];
        text[1]+=text[5];
    }
    public void g(){
        if (dp[1]<571){
            if ((Keys.w ||Keys.up||Keys.space)&&jumps<2&&u4&&u5&&u9){
                vel[1]=-22;
                onGround=false;
                jumps++;
                if (Keys.w){
                    u4=false;
                }
                if (Keys.up){
                    u5=false;
                }
                if (Keys.space){
                    u9=false;
                }
            }

            else if (vel[1]<20&&!onGround){
                vel[1]++;
            }
            if (!Keys.w){
                u4=true;
            }
            if (!Keys.up){
                u5=true;
            }
            if (!Keys.space){
                u9=true;
            }
            dp[1]+=vel[1];
        }
        else if (lvl==0){
            dp[1]=570;
            onGround=true;
            vel[1]=0;
            jumps=0;
            if (u8){
                stage++;
                attempt++;
            }
        }
        else if (lvl>=1){
            lvl--;
            dp[1]=20;
        }
    }
    public void bars(){
        for (int i=0; i<barriers[lvl].length;i++){
            if (collide(dp,barriers[lvl][i])){
                if (dp[1] + dp[3] > barriers[lvl][i][1] && vel[1] >= 0) {
                    dp[1] = barriers[lvl][i][1] - dp[3]; 
                    vel[1] = 0;                     
                    onGround = true;
                    jumps=0;
                }
                else if (dp[1]>barriers[lvl][i][1]-1){
                    dp[1]=barriers[lvl][i][1]+barriers[lvl][i][3]+1;
                }
                else if (dp[0] + dp[2] <= barriers[lvl][i][0] + 5) {
                    dp[0] = barriers[lvl][i][0] - dp[2];
                    vel[0] = 0;
                }
                else if (dp[0] >= barriers[lvl][i][0] + barriers[lvl][i][2] - 5) {
                    dp[0] = barriers[lvl][i][0] + barriers[lvl][i][2];
                    vel[0] = 0;
                }                
            }
        }
    }
    
    
    
    
    
    public boolean collide(int[] thing1, int[] thing2)
    {
         if(thing1[0]+thing1[2] <= thing2[0]           ||
            thing1[0]           >= thing2[0]+thing2[2] ||
            thing1[1]+thing1[3] <= thing2[1]           ||
            thing1[1]           >= thing2[1]+thing2[3])
         {
             return false;
         }
         else
         {
              return true;
         }
    }

    
}