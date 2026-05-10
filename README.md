# Nautilus Linux

Linux build of Nautilus

# Download

## Pre-Built Binaries

Pre-built binaries are not signed, which means your antivirus or smart-screen may flag it. Make sure you download
it from trusted sources only.

Binaries can be found in [Releases](https://github.com/nautiliday/nautilus-linux/releases) page.

## Building from source

### Pre-requisites

- JDK 21 or later
- Build Tool: Maven (optional, maven wrapper will be provided)

### Steps

- Clone the repository
```shell
git clone https://github.com/nautiliday/nautilus-linux.git
cd Nautilus
```
- Build the application
```shell
./mvnw -Pdist package jpackage:jpackage@linux
```

The generated binaries will be available in `../Nautilus/target/output`

> [!IMPORTANT]
> Note that each subsequent build requires manually clearing out the target folder because for some reason,
> generated binary stays in read-only mode and maven fails to replace or remove the read-only binary.

- The build uses `jpackage` to generate native app images
- Output format depends on the target OS
- A minimal runtime is generated using `jlink` which is then packaged into the target build
- This makes your build portable

# Screenshots

A modified build of Nautilus with hidden System UUID and Processor ID, running on Bazzite 43

<img width="850" height="640" alt="Screenshot_20260408_200545" src="https://github.com/user-attachments/assets/f0c95572-9232-420f-aec3-cd335d7b5aed" />

# License

[GPLv3](/LICENSE.md)
