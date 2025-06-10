javac -d out -cp "lib/*" @(Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })

jar cfm MarioAI.jar MANIFEST.MF -C out .

java -jar MarioAI.jar