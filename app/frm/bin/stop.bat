cd ..
for /f  %%i in (pid) do (taskkill /F /PID %%i)
del /F pid

echo "stopped"