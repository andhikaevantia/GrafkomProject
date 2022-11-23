package GameEngine.Engine;

import org.joml.Vector2d;
import org.joml.Vector2f;

import static org.lwjgl.glfw.GLFW.*;

public class MouseInput {

    private final Vector2d currentPos;

    private boolean leftButtonPressed = false;

    private boolean rightButtonPressed = false;
    private final Vector2f displVec;
    private final Vector2d previousPos;
    private boolean inWindow = false;

    private float scrollYOffset = 0;

    public MouseInput() {
        currentPos = new Vector2d(0, 0);
        displVec = new Vector2f(0,0);
        previousPos = new Vector2d(-1, -1);
    }

    public void init(Window window) {
//        glfwSetCursorPosCallback(window.getWindowHandle(), (windowHandle, xpos, ypos) -> {
//            currentPos.x = (xpos-window.getWidth()/2)/(window.getWidth()/2);
//            currentPos.y = -(ypos-window.getHeight()/2) / (window.getHeight()/2);
//        });
        glfwSetCursorPosCallback(window.getWindowHandle(), (windowHandle, xpos, ypos) -> {
            currentPos.x = xpos;
            currentPos.y = ypos;
        });
        glfwSetMouseButtonCallback(window.getWindowHandle(), (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });
        glfwSetCursorEnterCallback(window.getWindowHandle(), (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetScrollCallback(window.getWindowHandle(),
                ((windowHandle, xoffset, yoffset) -> {
            scrollYOffset = (float) yoffset;
        }));

    }

    public Vector2d getCurrentPos(){ return currentPos; }

    public void input(Window window) {
        displVec.x = 0;
        displVec.y = 0;
        if (previousPos.x > 0 && previousPos.y > 0 && inWindow) {
            double deltax = currentPos.x - previousPos.x;
            double deltay = currentPos.y - previousPos.y;
            boolean rotateX = deltax != 0;
            boolean rotateY = deltay != 0;
            if (rotateX) {
                displVec.y = (float) deltax;
            }
            if (rotateY) {
                displVec.x = (float) deltay;
            }
        }
        previousPos.x = currentPos.x;
        previousPos.y = currentPos.y;
    }


    public boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public void setLeftButtonPressed(boolean value){
        leftButtonPressed  = value;
    }

    public Vector2f getDisplVec() {
        return displVec;
    }

    public float getScrollYOffset() {
        return scrollYOffset;
    }

    public void setScrollYOffset(float scrollYOffset) {

        this.scrollYOffset = scrollYOffset;
    }
}
