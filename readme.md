# Remote Log Viewer

Remote Log Viewer (RLV) is a Desktop Application with the initial intention to view logs from a remote server via sftp easily. Since then the features have been extended and will be in the future as well. 


## Features

- easily view logs via sftp from a remote server
- automatic refresh every few seconds & autoscroll
- smootly download the newest log into your desired folder
- upload files to a remote server via sftp
- functional on Windows, MacOS & Linux
- automatic storage of login information
- logging of uploaded files in a session

## Installation

### Requirements

- Java 22+
- stable connection to your server

### Setup

Please download the newest version from [here](https://www.google.com/). Place the folder anywhere you like.
To start the application you just need to open the .jar file by clicking or in the command line:
```bash
java -jar RLV-1.0.0.jar
```
You may need to use root priviliges to execute the file in Linux.

#### Changing application config

As soon as the application opens go to the upper left corner with your mouse and hover to the settings. You need to select the Option "Change SSH file" and fill in the information. 
Do the same for the download path setting. 

## Usage

### Viewing a remote log

Viewing a remote log is really easy. Open the application and open the Log Viever tab. This tab opens by default. Nextly, fill in your credentials and click connect. If no error are beeing presented to you, you are good to go.

Be advised that you need to have a functional entry in your known hosts file. RLV is **not** capable of creating such an entry. That's why you need to connect to your remote server at least once in your terminal before using RLV.

## Authors

- [@FourtySeven](https://github.com/FourtySeven047)


## License

[MIT](https://choosealicense.com/licenses/mit/)
