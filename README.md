# Verifiable credentials Issuing

This repository contains an issuer for the Mattr api.

See CONTRIBUTING.md for information about commit messages and more.


# Mattr verifier
The verifier is in another [Repository](https://github.com/felleslosninger/dc24-wallet-verifier).

## Prerequisite
Here is a list for needed technologies for development.
- Ngrok, to let the callback get access to send back data.
- Mattr API, need the API to be able to interact with their ecosystem.

## Setup environment variables
Copy the ```.env.example``` file and rename the copy to ```.env```.
Then add your secret variables to the ```.env``` file, not the ```.env.example``` file.

What to change and what to set and where to find it.

### Java
Java 22 is needed to run the backend. Get java 22 [here](https://www.oracle.com/java/technologies/downloads/#jdk22-windows).

### Maven
Guide for maven download and installation can be found [here](https://phoenixnap.com/kb/install-maven-windows).

## Frontend Prerequisites
todo

## Presentation Flow

```mermaid
sequenceDiagram 
    box Users, Phone and Website
    Actor A as User
    participant W as Wallet
    END
    box Our application
    participant UA as User Agent
    participant AUTH as Authenticate
    participant I as Issuer
    participant db as Database
    end
    participant M as Mattr API
   

    A->>UA: Enter website
    UA->>AUTH: Authenticate to "Ansattporten" and select credentials
    AUTH->>I: Send the credential information
    I->>db: Save Credential and Unique Challenger
    I->>M: Presentation Request with Challenger
    M->>I: Return DIDCOM/QR COODE
    I->>UA: Display the QR code
    UA->>W: Scan the QR CODE
    W-->M: Request sent to Mattr
    M->>I: Send user DID key and Challenger
    I->>db: Lookup Challenger for Credentials
    db->>I: Return Credentials
    I->>M: Send web credential
    M->>I: Return signed web credential
    I->>M: Send signed credential for encryption
    M->>I: Return encrpted credential
    I->>M: Send encrypted credential to wallet
    M->>W: Send web credential to wallet 
    W->>A: Happy user

  
```