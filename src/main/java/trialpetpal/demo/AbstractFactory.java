package trialpetpal.demo;

public interface AbstractFactory<T, U extends Enum> {

  public T create(U type);

}
