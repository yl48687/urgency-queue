# Urgency Queue
The project encompasses two distinct implementations of the `UrgencyQueue` interface, focusing on managing a queue of items ordered by their urgency level. An urgency queue prioritizes items based on their relative level of urgency rather than their arrival time. This project offers concrete implementations through the `LinkedUrgencyQueue` and `CustomLinkedUrgencyQueue` classes.

## Design Overview
Both implementations extend the abstract class `BaseLinkedUrgencyQueue`, which provides shared functionalities and ensures code reusability. The classes adhere to the `UrgencyQueue` interface, defining essential operations for managing urgency queues. The design promotes modularity and extensibility, allowing for easy integration of additional functionalities.

## Functionality
`BaseLinkedUrgencyQueue`:
- Creates an abstract class implementing a subset of methods defined in the `UrgencyQueue` interface.
- Implementation ensures flexibility by not relying on specific underlying data structures.
- Manages instance variables and implements methods according to the interface requirements.

`LinkedUrgencyQueue`:
- Extends `BaseLinkedUrgencyQueue` to implement the `UrgencyQueue` interface.
- Provides default constructor for initializing the queue with linked list-based storage.
- Implements operations for adding, removing, and querying items based on their urgency level.

`CustomLinkedUrgencyQueue`:
- Extends `BaseLinkedUrgencyQueue` to implement the `UrgencyQueue` interface.
- Constructor accepts a comparator for customizing item comparison.
- Utilizes linked list structure for efficient management of items.

## File Structure and Contents
```
urgency-queue/
├── README.md
├── src
│   ├── BaseLinkedUrgencyQueue.java
│   ├── CustomLinkedUrgencyQueue.java
│   └── LinkedUrgencyQueue.java
└── urgency-queue.jar
```
