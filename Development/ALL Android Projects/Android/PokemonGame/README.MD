# 🗺️ Simple Pokemon Game

## 📝 Overview

An Android-based location game where players use GPS to locate and "catch" Pokémon displayed on a Google Map.

---

## 🚀 Key Features

### 🗺️ Google Maps Integration
- Displays the player's real-time location  
- Shows nearby Pokémon on an interactive map  

### 📍 GPS Tracking
- Uses device location services  
- Tracks player movement dynamically  

### 🎯 Catching Mechanics
- Automatically catches a Pokémon  
- Triggered when the player is within 2 meters of its coordinates  

### ⚡ Power System
- Accumulates "Power" after catching Pokémon  
- Different characters provide different power values  

### 🧭 Custom Markers
- Each Pokémon has:
  - Unique icon  
  - Name  
  - Description  
  - Specific map coordinates  

---

## 🛠️ Technologies Used

- **Language:** Kotlin  
- **Maps API:** Google Maps SDK for Android  
- **Location Services:** Android LocationManager & Runtime Permissions  
- **UI Layout:** XML (Map Fragment)  

---

## 📂 Project Structure

- `MapsActivity.kt` → Handles map setup, GPS updates, and catching logic  
- `Pokemon.kt` → Data class defining Pokémon attributes (name, power, location)  
- `activity_maps.xml` → Layout file containing the map fragment  
- `AndroidManifest.xml` → Configures permissions (INTERNET, GPS) and API key  

---

## 🎯 Purpose of the Project

This project was developed to practice:
- Google Maps API integration  
- Location-based services in Android  
- Runtime permission handling  
- Working with coordinates and distance calculations  

---

### 👨‍💻 Author
Abdelilah Aharcha  
Android Developer (Java & Kotlin)
