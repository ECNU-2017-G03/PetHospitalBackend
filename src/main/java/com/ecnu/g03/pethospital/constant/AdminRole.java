package com.ecnu.g03.pethospital.constant;

/**
 * @author Shen Lei
 * @date 2021/3/28 21:52
 */
public enum AdminRole {

    /**
     * super admin, who can manage normal admin
     */
    SUPER("super"),
    /**
     * normal admin
     */
    NORMAL("normal");

    private String description;

    AdminRole(String description) {
        this.description = description;
    }

    public static AdminRole fromString(String description) {
        for (AdminRole adminRole : AdminRole.values()) {
            if (adminRole.description.equals(description)) {
                return adminRole;
            }
        }
        throw new IllegalArgumentException("No description " + description + " of enum type AdminRole");
    }

    @Override
    public String toString() {
        return this.description;
    }

}
