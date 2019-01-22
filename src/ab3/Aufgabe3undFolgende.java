package ab3;

import ab3.math.Vector3;
import ab3.objects.Cube;
import ab3.objects.Object;
import ab3.objects.Sphere;
import lenz.opengl.AbstractOpenGLBase;
import lenz.opengl.Texture;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL30.*;

public class Aufgabe3undFolgende extends AbstractOpenGLBase {

    private InputHandler inputHandler;

    private List<Object> objects;
    private Cube cube;
    private Cube cube2;
    private Cube cube3;
    private Sphere sphere;

    private List<Texture> textures;

    private Light light;

    private int[] counter;

    public static void main(String[] args) {
        new Aufgabe3undFolgende().start("CG Aufgabe 3", 700, 700);
    }

    @Override
    protected void init() {
        objects = new ArrayList<>();
        objects.add(cube = new Cube(1, 0, -2, "aufgabe3"));
        objects.add(cube2 = new Cube(-2.25f, 2.25f, -3, "aufgabe3"));
        objects.add(cube3 = new Cube(0, -1.5f, -2, "aufgabe3"));
        objects.add(sphere = new Sphere(-1, 0, -2, "sphere"));

        counter = new int[objects.size()];

        //z-Buffer und backface culling aktivieren
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_CULL_FACE);

        //Light
        light = new Light(new Vector3(2, 4, 1.5f), new Vector3(0.8f, 0.8f, 0.8f), 5);

        //Textures
        textures = new ArrayList<>();
        textures.add(new Texture("logo.png"));
        textures.add(new Texture("metal.jpg"));
        textures.add(new Texture("bricks.png", 4, true, GL_NEAREST, GL_NEAREST_MIPMAP_NEAREST));


        //Input
        inputHandler = new InputHandler(window);

        //WASD QE keys for sphere movement
        inputHandler.registerKeyCallback(GLFW_KEY_W, () -> sphere.getModelMatrix().translate(0, 0.04f, 0));
        inputHandler.registerKeyCallback(GLFW_KEY_A, () -> sphere.getModelMatrix().translate(-0.04f, 0, 0));
        inputHandler.registerKeyCallback(GLFW_KEY_S, () -> sphere.getModelMatrix().translate(0, -0.04f, 0));
        inputHandler.registerKeyCallback(GLFW_KEY_D, () -> sphere.getModelMatrix().translate(0.04f, 0, 0));
        inputHandler.registerKeyCallback(GLFW_KEY_Q, () -> sphere.getModelMatrix().translate(0, 0, 0.04f));
        inputHandler.registerKeyCallback(GLFW_KEY_E, () -> sphere.getModelMatrix().translate(0, 0, -0.04f));

        //Arrow keys for light movement
        inputHandler.registerKeyCallback(GLFW_KEY_UP, () -> light.moveUp());
        inputHandler.registerKeyCallback(GLFW_KEY_DOWN, () -> light.moveDown());
        inputHandler.registerKeyCallback(GLFW_KEY_LEFT, () -> light.moveLeft());
        inputHandler.registerKeyCallback(GLFW_KEY_RIGHT, () -> light.moveRight());
    }

    @Override
    public void update() {
        // Transformation durchführen (Matrix anpassen)
        if (counter[0] < 45) {
            cube.getModelMatrix().rotateX(1);
        } else {
            cube.getModelMatrix().rotateY(1);
        }
        counter[0] = (counter[0] + 1) % 90;

        if (counter[1] < 45) {
            cube2.getModelMatrix().translate(0.1f, 0, 0);
            cube2.getModelMatrix().scale(1, 1, 1.03f);
        } else if (counter[1] < 90) {
            cube2.getModelMatrix().translate(0, -0.1f, 0);
            cube2.getModelMatrix().scale(1, 1, 1 / 1.03f);
        } else if (counter[1] < 135) {
            cube2.getModelMatrix().translate(-0.1f, 0, 0);
            cube2.getModelMatrix().scale(1, 1, 1.03f);
        } else {
            cube2.getModelMatrix().translate(0, 0.1f, 0);
            cube2.getModelMatrix().scale(1, 1, 1 / 1.03f);
        }
        counter[1] = (counter[1] + 1) % 180;

        if (counter[2] < 180) {
            cube3.getModelMatrix().translate(0, 0, -0.02f);
        } else {
            cube3.getModelMatrix().translate(0, 0, 0.02f);
        }
        counter[2] = (counter[2] + 1) % 360;

        inputHandler.processInput();
    }

    @Override
    protected void render() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        for (int i = 0; i < objects.size(); i++) {
            Object object = objects.get(i);
            object.bindShader();
            int shaderID = object.getShaderProgram().getId();

            int uniformLightPositionID = glGetUniformLocation(shaderID, "lightPosition");
            int uniformLightColorID = glGetUniformLocation(shaderID, "lightColor");
            int uniformLightIntensityID = glGetUniformLocation(shaderID, "lightIntensity");
            glUniform3fv(uniformLightPositionID, light.getPosition().getValues());
            glUniform3fv(uniformLightColorID, light.getColor().getValues());
            glUniform1f(uniformLightIntensityID, light.getIntensity());

            if (i < textures.size() && textures.get(i) != null)
                glBindTexture(GL_TEXTURE_2D, textures.get(i).getId());

            int uniformModelMatrixID = glGetUniformLocation(shaderID, "modelMatrix");
            glUniformMatrix4fv(uniformModelMatrixID, false, object.getModelMatrix().getValuesAsArray());
            object.render();
        }
    }

    @Override
    public void destroy() {
    }
}
