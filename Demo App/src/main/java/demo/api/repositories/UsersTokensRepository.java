package demo.api.repositories;

public interface UsersTokensRepository {
    void addTokenFor(int id, String token);

    void deleteUserToken(int id);

    void deleteUserToken(String token);

    Integer getUserIdForToken(String token);
}
