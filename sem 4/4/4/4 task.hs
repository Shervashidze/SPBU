import System.IO
import System.IO.Error

data Entry = 
    Entry {
        name :: String,
        phone :: String
    }

instance Show Entry where
    show entry = name entry ++ " " ++ phone entry ++ "\n" 

data Phonebook = Phonebook [Entry]

instance Show Phonebook where
    show (Phonebook entries) = concatMap show entries

findEntry :: Phonebook -> (Entry -> Bool) -> Phonebook
findEntry (Phonebook entries) condition = Phonebook (filter condition entries)

addEntry :: Phonebook -> Entry -> Phonebook
addEntry (Phonebook entries) newRec = Phonebook (newRec : entries)

save :: Phonebook -> String -> IO()
save phonebook path = do
    writeFile path (show phonebook)

load :: String -> IO(Phonebook)
load path = do
    file <- readFile path
    return $ Phonebook (map ((\x -> Entry (x !! 0) (x !! 1)) . words) $ lines file)


main = do
    hSetBuffering stdin LineBuffering
    putStrLn "Phonebook:"
    putStrLn "1 name phone - add"
    putStrLn "2 name - find phone by name"
    putStrLn "3 phone - find name by phone"
    putStrLn "4 fileName - save into file"
    putStrLn "5 fileName - load from file"
    putStrLn "0 - exit"
    doLoop (Phonebook [])

doLoop phonebook = do
    putStr "Phonebook >> "
    command <- getLine
    case (words command) of
        "1":newName:newPhone:[] -> do
            doLoop (addEntry phonebook (Entry newName newPhone))
        "2":findName:[] -> do
            putStrLn $ show $ findEntry phonebook (\x -> name x == findName)
            doLoop phonebook
        "3":findPhone:[] -> do
            putStrLn $ show $ findEntry phonebook (\x -> phone x == findPhone)
            doLoop phonebook
        "4":saveFile:[] -> do
            save phonebook saveFile
            doLoop phonebook
        "5":loadFile:[] -> do
            newPhonebook <- tryIOError (load loadFile)
            case newPhonebook of
                Left e -> do
                    putStrLn "File reading error."
                    doLoop phonebook
                Right correctResult -> doLoop correctResult
        "0":[] ->
            putStrLn "Bye"
        _ -> do
            putStrLn "Incorrect command"
            doLoop phonebook