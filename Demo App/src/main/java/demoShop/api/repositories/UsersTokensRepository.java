package demoShop.api.repositories;

public interface UsersTokensRepository {
    String createTokenFor(int id);

    void deleteUserToken(int id);

    void deleteUserToken(String token);

    Integer getUserIdForToken(String token);
}
