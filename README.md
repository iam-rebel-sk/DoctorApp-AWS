
# <h1 align="center"> Doctor Appointment Booking App </h1>
<p align="center">
<a href="Java url">
<img alt="Java" src="https://img.shields.io/badge/Java-21-darkblue.svg" />
</a>
<a href="Maven url" >
<img alt="Maven" src="https://img.shields.io/badge/maven-4.0-brightgreen.svg" />
</a>
<a href="Spring Boot url" >
<img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.1.3-brightgreen.svg" />

</p>

 

---

 

<p align="left">

## Overview
This Java backend project provides a RESTful API for managing patients, doctors, and administrators in a healthcare system. It offers various endpoints to perform operations such as user authentication, appointments scheduling, and user management.




## API Endpoints

#### Patient Controller

```http
POST /patient/signUp
```

 Description                |
 :------------------------- |
Allows patients to sign up for the system. |

```http
POST /patient/signIn
```

 Description                |
 :------------------------- |
Allows patients to sign in to the system. |

```http
POST /patient/appointment/schedule
```

 Description                |
 :------------------------- |
Allows patients to schedule appointments. |

```http
PUT /patient/forget/password
```

 Description                |
 :------------------------- |
Allows patients to reset their passwords if forgotten. |

```http
PUT /patient/change/password
```

 Description                |
 :------------------------- |
Allows patients to change their passwords. |

```http
GET /patient/{id}
```

 Description                |
 :------------------------- |
Retrieves patient information based on their ID |

```http
DELETE /patient/appointment/{appointmentId}/cancel
```

 Description                |
 :------------------------- |
Cancels a patient's scheduled appointment. |

```http
DELETE /patient/signOut
```

 Description                |
 :------------------------- |
Allows patients to sign out of the system |

#### Admin Controller
```http
POST /admin/signUp
```

 Description                |
 :------------------------- |
Allows administrators to sign up for the system. |

```http
POST /admin/signIn
```

 Description                |
 :------------------------- |
Allows administrators to sign in to the system. |


```http
POST /doctor
```

 Description                |
 :------------------------- |
Creates a new doctor in the system. |


```http
PUT /admin/forget/password
```

 Description                |
 :------------------------- |
Allows administrators to reset their passwords if forgotten. |


```http
PUT /admin/change/password
```

 Description                |
 :------------------------- |
Allows administrators to change their passwords. |


```http
GET /patients/{bloodGroup}/bloodGroup
```

 Description                |
 :------------------------- |
Retrieves a list of patients based on their blood group. |


```http
DELETE /doctor/{id}
```

 Description                |
 :------------------------- |
Deletes a doctor from the system based on their ID. |


```http
DELETE /admin/signOut
```

 Description                |
 :------------------------- |
Description: Allows administrators to sign out of the system. |

#### Doctor Controller
```http
GET /doctor/{id}
```

 Description                |
 :------------------------- |
Retrieves doctor information based on their ID. |







## Schemas

The project uses the following data transfer objects (DTOs) and data models:

`EmailVerifierDTO :` Data transfer object for email verification.

`ForgetPasswordDTO: `  Data transfer object for password reset requests.

`AuthenticationDTO: `   Data transfer object for user authentication.

`ChangePasswordDTO: `   Data transfer object for password change requests.

`Appointment: ` Model representing appointment information.

`Doctor: ` Model representing doctor information.

`Patient: ` Model representing patient information.

`SignInInputDTO: ` Data transfer object for user sign-in.

`AppointmentScheduleDTO: ` Data transfer object for scheduling appointments.

`DoctorAddingDTO: `  Data transfer object for adding doctors.

`Admin: ` Model representing administrator information.


## Deployment

Follow these steps to set up and run the project:



- `Prerequisites: ` Ensure you have Java and your preferred IDE (e.g., IntelliJ, Eclipse) installed.

- `Clone the Repository: `Clone this repository to your local machine.

- `Configuration:` Set up the database configuration and ensure all necessary dependencies are installed.

- `Build and Run:` Build the project and run the application.

- `Testing:` Test the API using tools like Postman or a web browser by sending requests to the defined endpoints.

## Technologies Used

- **Framework:** Spring Boot
- **Language:** Java
- **Build Tool:** Maven
- **Database:** MySQL Workbench

## Contact
For questions or feedback, please contact [Rebel Sk](mailto:iamrebelsk@gmail.com).


