package demoShop.api.repositories;

public interface UsersTokensRepository {
    String createTokenFor(int id);

    void deleteUserToken(int id);

    Integer getUserIdForToken(String token);
}
