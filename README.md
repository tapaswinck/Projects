# Bounded Particle Simulation (JavaFX)

This project is a visual simulation of particles confined within a bounded area, implemented using JavaFX. The simulation allows users to interact with particles and observe dynamic behaviors like motion and color changes.

## Features
- **Interactive Particle Movement**: Click anywhere on the canvas to apply forces that move the particles.
- **Dynamic Particle Addition**: Add more particles with a button click, increasing the complexity of the simulation.
- **Customizable Colors**: Change the colors of all particles randomly with a button click.

## Technical Details
- **Animation**: Utilizes `AnimationTimer` for smooth frame updates.
- **Graphics**: Renders particles using `Canvas` and `GraphicsContext`.
- **Event Handling**: Responds to mouse clicks to apply directional forces to the particles.
- **Object-Oriented Design**: Encapsulates particle behavior in the `ColoredParticle` class, ensuring modularity and scalability.

## How to Run
1. Clone the repository.
2. Open the project in an IDE with JavaFX support (e.g., IntelliJ IDEA, Eclipse).
3. Build and run the `BoundedParticleSimulationJavaFX` main class.

## Future Improvements
- Add collision detection between particles.
- Implement particle trails for a more dynamic visual effect.
- Allow user-defined particle properties (e.g., size, speed).


