# AI Core Microservice (Archived)

> **Status:** Archived
> This project represents a foundational experiment in integrating AI capabilities into Spring Boot applications using Gemini 2.0 Flash.

---

## Overview

This microservice accepts AI prompts (custom or preset) via REST endpoints and returns generated responses from the Gemini API. It is designed as a **pluggable backend service** to support broader AI use cases within Spring-based ecosystems.

---

## Features

-  Generic `/prompt` endpoint for dynamic prompt handling  
-  Preset endpoint for fixed, reusable AI queries  
-  Load test endpoint for high-concurrency testing (mocked mode)  
-  Modular client-service-controller architecture for extensibility  
-  API key managed via environment-safe configuration

---

##  Tech Stack

- **Spring Boot 3.5**
- **Java 17+**
- **Gemini 2.0 Flash** (via REST API)
- **Postman** (for load and functional testing)

---

## Why Archived?

> After initial development and testing, it was clear that the service:
>
> - Offered **limited unique value** beyond direct Gemini API calls  
> - Required **more specific business logic** to be impactful  
> - Was better used as a **learning milestone** than a deployable product  

---

## Lessons Learned

- Not all API integrations justify a microservice on their own  
- Microservices need a **distinct business purpose** or workflow to be defensible  
- Load testing, modularity, and abstraction are valuable—but need application context

---
