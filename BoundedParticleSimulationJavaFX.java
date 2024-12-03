package com.example.multicoloredparticlesimulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class ColoredParticle {
    private static final int DIAMETER = 20;
    double x;
    double y;
    private double vx, vy;
    Color color;
    private double minX, minY, maxX, maxY;

    public ColoredParticle(double x, double y, Color color, double minX, double minY, double maxX, double maxY) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
        this.color = color;
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public void update() {
        x += vx;
        y += vy;

        x = Math.max(minX + DIAMETER / 2, Math.min(x, maxX - DIAMETER / 2));
        y = Math.max(minY + DIAMETER / 2, Math.min(y, maxY - DIAMETER / 2));
    }

    public void applyForce(double forceX, double forceY) {
        vx += forceX;
        vy += forceY;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - DIAMETER / 2, y - DIAMETER / 2, DIAMETER, DIAMETER);
    }
}

public class BoundedParticleSimulationJavaFX extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int NUM_PARTICLES = 5;

    private List<ColoredParticle> particles = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);

        Button addParticlesButton = new Button("Add Particles");
        addParticlesButton.setOnAction(e -> addParticles());

        Button changeColorsButton = new Button("Change Colors");
        changeColorsButton.setOnAction(e -> changeColors());

        HBox buttonBox = new HBox(10, addParticlesButton, changeColorsButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(canvas, buttonBox);
        vbox.setAlignment(Pos.CENTER);

        // Adjust the alignment to bring the buttons slightly up
        vbox.setTranslateY(-10);

        root.getChildren().add(vbox);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bounded Particle Simulation");
        primaryStage.show();
        primaryStage.setResizable(false);

        initializeParticles();

        scene.setOnMouseClicked(this::handleMouseClicked);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer() {
            @Override
            public void handle(long now) {
                for (ColoredParticle particle : particles) {
                    particle.update();
                }

                gc.clearRect(0, 0, WIDTH, HEIGHT);

                for (ColoredParticle particle : particles) {
                    particle.draw(gc);
                }
            }
        }.start();
    }

    private void initializeParticles() {
        Random random = new Random();

        for (int i = 0; i < NUM_PARTICLES; i++) {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            Color color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            particles.add(new ColoredParticle(x, y, color, 0, 0, WIDTH, HEIGHT));
        }
    }

    private void handleMouseClicked(MouseEvent event) {
        for (ColoredParticle particle : particles) {
            double forceX = (event.getX() - particle.x) * 0.01;
            double forceY = (event.getY() - particle.y) * 0.01;
            particle.applyForce(forceX, forceY);
        }
    }

    private void addParticles() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            double x = random.nextDouble() * WIDTH;
            double y = random.nextDouble() * HEIGHT;
            Color color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
            particles.add(new ColoredParticle(x, y, color, 0, 0, WIDTH, HEIGHT));
        }
    }

    private void changeColors() {
        Random random = new Random();
        for (ColoredParticle particle : particles) {
            particle.color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
