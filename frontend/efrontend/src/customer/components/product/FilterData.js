// export const colors=[
//     "White",
//     "Black",
//     "Red",
//     "Pink",
//     "Green",
//     "Blue",
//     "Yellow"
// ]

export const filters=[
    {
        id:"color",
        name:"Color",
        options:[
            {value:"white" ,label:"White"},
            {value:"black" ,label:"Black"},
            {value:"blue" ,label:"Blue"},
            {value:"green" ,label:"Green"},
            {value:"yellow" ,label:"Yellow"},
            {value:"pink" ,label:"Pink"},
            {value:"red" ,label:"Red"},
        ]
    },
    {
        id:"size",
        name:"Size",
        options:[
            {value:"s" ,label:"S"},
            {value:"m" ,label:"M"},
            {value:"L" ,label:"L"}
        ]
    },
];

export const singleFilter=[

    {
        id:"price",
        name:"Price",
        options:[
            {value:"199-399" ,label:"159-399"},
            {value:"399-499" ,label:"399-499"},
            {value:"499-599" ,label:"499-599"},
            {value:"599-799" ,label:"599-799"},
            {value:"799-999" ,label:"799-999"},
            {value:"999-1200" ,label:"999-1200"},
        ]
    },
    {
        id:"discount",
        name:"Discount",
        options:[
            {value:"20%" ,label:"20% and Above"},
            {value:"30%" ,label:"30% and Above"},
            {value:"40%" ,label:"40% and Above"},
            {value:"50%" ,label:"50% and Above"},
            {value:"60%" ,label:"60% and Above"},
        ]
    },
    {
        id:"stock",
        name:"Availability",
        options:[
            {value:"in_stock",label:"in Stock"},
            {value:"out_of_stock",label:"Out Of Stock"}
        ]
    }
]