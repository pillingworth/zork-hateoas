package uk.co.threeonefour.zorkhateoas.rest;

public final class ApiMetadata {
  private final String name;
  private final String description;
  private final String copyright;

  public ApiMetadata(String name, String description, String copyright) {
    this.name = name;
    this.description = description;
    this.copyright = copyright;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getCopyright() {
    return copyright;
  }
}
