package demoShop.api;

public interface UsersTokensRepository {
    void createTokenFor(int id);

    void deleteUserToken(int id);
}
