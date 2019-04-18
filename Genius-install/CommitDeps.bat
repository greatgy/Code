@echo off
FOR %%A IN (
	"E:\MyWork2\Genius-deps"
) DO START TortoiseProc.exe /command:commit /path:%%A /closeonend:0