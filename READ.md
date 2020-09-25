# Shipping Zip Code

Shipping zip code is a spring boot service used to calculate the restricted zip code based on inputs.

## Installation

To install you need to have java 8 and gradle installed on the system.

```bash
gradle clean build
gradle bootRun
```

## Usage
Rest Endpoint is specified in the service with the following url:

POST   http://localhost:8080/api

JsonBody


[
	{
		"lowerFrequency": 94133,
		"higherFrequency":94133
	},
	{
		"lowerFrequency": 94101,
		"higherFrequency":94200
	},
	{
		"lowerFrequency": 94300,
		"higherFrequency":94399
	}]

Response:

[
    {
        "lowerFrequency": 94101,
        "higherFrequency": 94200
    },
    {
        "lowerFrequency": 94300,
        "higherFrequency": 94399
    }
]




