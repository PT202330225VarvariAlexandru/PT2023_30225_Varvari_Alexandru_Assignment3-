package Model;

import java.time.LocalDateTime;

public record Bill(int id, int clientId, int productId, int quantity, LocalDateTime orderDate) {}
