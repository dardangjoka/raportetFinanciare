package dev.melion.raportet.Financiare.model;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;


@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Nullable
    private ObjectId id;
    private String username;
    private String email;

    public User(String username, String email) {
        this.email = email;
        this.username = username;
    }
}
