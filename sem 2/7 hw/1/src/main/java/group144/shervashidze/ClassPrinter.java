package group144.shervashidze;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

/** Class printer to get information about class. */
public class ClassPrinter {

    /**
     * Reflects the class.
     * @param clazz to get information.
     * @return the string containing information about this class.
     */
    public static String reflect(Class clazz) {
        return getClassDeclaration(clazz)
                + " {\n"
                + getFields(clazz) + getConstructors(clazz) + getMethods(clazz) + getInnerClasses(clazz)
                + "\n}";
    }

    /**
     * Class Declaration.
     * @param clazz to get information.
     * @return class declaration as string: modifiers + simple name + type parameters + superclass + interfaces.
     */
    private static String getClassDeclaration(Class clazz) {
        return getModifiers(clazz) + "class " + simpleNameWithParameters(clazz) + getSuperclass(clazz)
                + getInterfaces(clazz);
    }

    /**
     * Get Modifiers.
     * @param clazz to get information.
     * @return string - modifiers of clazz, "" if there is no modifier.
     */
    private static String getModifiers(Class clazz) {
        if (clazz.getModifiers() != 0) {
            return Modifier.toString(clazz.getModifiers()) + " ";
        } else {
            return "";
        }
    }

    /**
     *
     * @param clazz to get information.
     * @return String: " extends " + superclass simple name, "" if there is no superclass.
     */
    private static String getSuperclass(Class clazz) {
        if (clazz.getSuperclass() != null) {
            return " extends " + simpleNameWithParameters(clazz.getSuperclass());
        } else {
            return "";
        }
    }

    /**
     *
     * @param clazz to get information.
     * @return string with interfaces of this class.
     */
    private static String getInterfaces(Class clazz) {
        if (clazz.getInterfaces().length == 0) {
            return "";
        } else {
            return Arrays.stream(clazz.getInterfaces()).map(ClassPrinter::simpleNameWithParameters)
                    .collect(Collectors.joining( ", ", " implements ", ""));
        }
    }

    /**
     * Simple name with parameters.
     * @param clazz to take simple name with type parameters.
     * @return String - simple name + "<" + type parameters(if more than 1 delimiter = ", ") + ">".
     */
    private static String simpleNameWithParameters(Class clazz) {
        return clazz.getSimpleName() + getTypeParameters(clazz);
    }

    /**
     *
     * @param clazz to get information.
     * @return string as "<" + type parameters + ">".
     */
    private static String getTypeParameters(Class clazz) {
        if (clazz.getTypeParameters().length == 0) {
            return "";
        } else {
            return Arrays.stream(clazz.getTypeParameters())
                    .map(Type::getTypeName)
                    .collect(Collectors.joining(", ", "<", ">"));
        }
    }

    /**
     * class fields to String.
     * @param clazz to take information.
     * @return empty string if there is no declared fields. Otherwise return all fields as
     *                                                      string: declared field + ";".
     */
    private static String getFields(Class clazz) {
        if (clazz.getDeclaredFields().length != 0) {
            return Arrays.stream(clazz.getDeclaredFields()).map(ClassPrinter::getFieldDeclaration)
                    .collect(Collectors.joining(";\n\t", "\t", ";\n"));
        } else {
            return "";
        }
    }

    /**
     * Get field declaration.
     * @param field to take information
     * @return field declaration as string: modifiers + " " + type simple name + " " + field name.
     */
    private static String getFieldDeclaration(Field field) {
        String modifiers = field.getModifiers() != 0 ? Modifier.toString(field.getModifiers()) + " " : "";
        return modifiers + field.getType().getSimpleName() + " " + field.getName();
    }

    /**
     *
     * @param clazz to get information.
     * @return string with constructors of the class.
     */
    private static String getConstructors(Class clazz) {
        if (clazz.getDeclaredConstructors().length != 0) {
            return Arrays.stream(clazz.getDeclaredConstructors())
                    .map(constructor -> getConstructor(clazz, constructor))
                    .collect(Collectors.joining(";\n\t", "\n\t", ";\n"));
        } else {
            return "";
        }

    }

    /**
     *
     * @param clazz to get name.
     * @param constructor to get information about constructor.
     * @return string with constructor.
     */
    private static String getConstructor(Class clazz, Constructor constructor) {
        String modifiers = constructor.getModifiers() != 0 ? Modifier.toString(constructor.getModifiers()) + " " : "";
        return modifiers + clazz.getSimpleName() +
                Arrays.stream(constructor.getParameters()).map(p -> p.getType() + " " + p.getName())
                .collect(Collectors.joining(", ", "(", ")"));
    }

    /**
     *
     * @param clazz to get information.
     * @return string with methods of this class.
     */
    private static String getMethods(Class clazz) {
        if (clazz.getDeclaredMethods().length != 0) {
            return Arrays.stream(clazz.getDeclaredMethods())
                    .map(ClassPrinter::getMethod)
                    .collect(Collectors.joining(";\n\t", "\n\t", ";"));
        } else {
            return "";
        }
    }

    /**
     *
     * @param method to get information
     * @return string as modifiers + name + exceptions.
     */
    private static String getMethod(Method method) {
        String modifiers = method.getModifiers() == 0 ? "" : Modifier.toString(method.getModifiers()) + " ";
        String exceptions = method.getExceptionTypes().length == 0 ? "" :
                Arrays.stream(method.getExceptionTypes()).map(Type::getTypeName)
                        .collect(Collectors.joining(", ", " throws ", ""));

        return modifiers + method.getReturnType() + " " + method.getName() +
                Arrays.stream(method.getParameters()).map(p -> p.getType() + " " + p.getName())
                .collect(Collectors.joining(", ", "(", ")")) + exceptions;
    }

    /**
     *
     * @param clazz to get information.
     * @return string with information about each inner class using previous ClassPrinter methods.
     */
    private static String getInnerClasses(Class clazz) {
        StringBuilder result = new StringBuilder();
        for (Class<?> innerClass : clazz.getDeclaredClasses()) {
            result.append(reflect(innerClass));
        }
        return "\n" + result.toString();
    }
}