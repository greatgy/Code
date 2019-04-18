@echo off
FOR %%A IN (
  	"E:\MyWork2\Proj-supergenius-XO"
	"E:\MyWork2\Proj-supergenius-server"
	"E:\MyWork2\Proj-supergenius-admin"
	"E:\MyWork2\Proj-supergenius-finance"
	"E:\MyWork2\Proj-supergenius-official"
	"E:\MyWork2\Proj-supergenius-user"
	"E:\MyWork2\Proj-supergenius-tpi"
	"E:\MyWork2\Proj-supergenius-moral"
	"E:\MyWork2\Proj-supergenius-account"
	"E:\MyWork2\Proj-supergenius-data"
	"E:\MyWork2\Proj-supergenius-manager"
        "E:\MyWork2\Proj-supergenius-enterpriser"
	"E:\MyWork2\Proj-supergenius-wechat"
) DO START TortoiseProc.exe /command:update /path:%%A