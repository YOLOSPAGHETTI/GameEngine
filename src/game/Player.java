package game;

import ui.PlayerAnimationController;
import ui.ResourceLoader;
import ui.Sprite;

public class Player {
    PlayerAnimationController pac;

    private float touchXStart;
    private float touchYStart;
    private long lastTouchTime;
    private int blocking;

    public Player(PlayerAnimationController pac) {
        // setControls(image);

        this.pac = pac;
    }

    /*private boolean canAct() {
        return ps.currentAnimation == ps.idleAnimation;
    }*/

    private void stab(int direction) {
        //System.out.println("stab: "+direction);
        if(direction == 0) {
            pac.runAnimation(ResourceLoader.playerStabLeftAnimation);
        }
        else if(direction == 1) {
            pac.runAnimation(ResourceLoader.playerStabMidAnimation);
        }
        else if(direction == 2) {
            pac.runAnimation(ResourceLoader.playerStabRightAnimation);
        }
    }

    private void cleave(int direction) {
        //System.out.println("cleave: "+direction);
        if(direction == 0) {
            pac.runAnimation(ResourceLoader.playerCleaveLeftAnimation);
        }
        else if(direction == 2) {
            pac.runAnimation(ResourceLoader.playerCleaveRightAnimation);
        }
    }
    
    public PlayerAnimationController getAnimationController() {
    	return pac;
    }

    /*private void setControls(View image) {
        image.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //System.out.println("DownTime: "+event.getDownTime());
                //System.out.println("EventTime: "+event.getEventTime());
                //System.out.println("Pressure: "+event.getPressure());
                float x = event.getX();
                float y = event.getY();


                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    lastTouchTime = System.currentTimeMillis();
                    touchXStart = x;
                    touchYStart = y;
                    return true;
                }
                else if (event.getAction() == MotionEvent.ACTION_UP) {
                    float differenceX = touchXStart-x;
                    float differenceY = touchYStart-y;
                    float distance = (float)Math.sqrt(differenceX*differenceX+differenceY*differenceY);

                    long timeDifference = System.currentTimeMillis() - lastTouchTime;
                    //System.out.println("1: "+timeDifference);

                    if(distance > 100) {
                        float directionX = differenceX/distance;
                        float directionY = differenceY/distance;

                        if(directionY>=0.5) {
                            if(directionX>=-0.9 && directionX<-0.3) {
                                //if (canAct())
                                    stab(2);
                            }
                            else if(directionX>=-0.3 && directionX<=0.3) {
                                //if (canAct())
                                    stab(1);
                            }
                            else if(directionX>0.3 && directionX<=0.9) {
                                //if (canAct())
                                    stab(0);
                            }
                        }
                        else if(directionX>=0.5) {
                            //if (canAct())
                                cleave(0);
                        }
                        else if(-directionX>=0.5) {
                            //if (canAct())
                                cleave(2);
                        }
                        return true;
                    }
                    else if(touchXStart >= 500 && touchXStart <= 650 && touchYStart >= 50 && touchYStart <= 150) {
                        if(timeDifference > 250) {
                            if(touchXStart >= 500 && touchXStart < 550) {
                                blocking = 0;
                                    //Debug.Log("LeftBlock");
                            }
                            else if(touchXStart >= 550 && touchXStart <= 600) {
                                blocking = 1;
                                //Debug.Log("MidBlock");
                            }
                            else {
                                blocking = 2;
                                //Debug.Log("RightBlock");
                            }
                        }
                    }
                }
                return false;
            }
        });
    }*/
}
