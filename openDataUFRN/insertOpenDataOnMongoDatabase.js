const fs = require("fs");
const { parse } = require("csv-parse");
const { MongoClient } = require("mongodb");

const uri = "mongodb+srv://bravo:9TZchMenuoTphDkm@cluster0.r3uzzvx.mongodb.net/";
const dbName = "development";
const filesToInsert = [
    "./inPersonSubjects.csv",
    "./semiInPersonSubjects.csv",
    "./onlineSubjects.csv"
]

const getSubjectFieldFrom = {
    componentID: 0,
    componentType: 1,
    code: 2,
    level: 3,
    name: 4,
    department: 5,
    theoreticalHours: 6,
    practicalHours: 7,
    internshipHours: 8,
    totalHours: 9,
    teacherDedicatedHours: 10,
    eadHours: 11,
    maxEadCredits: 12,
    flexibleScheduleAllowed: 13,
    unitQuantity: 14,
    evaluationProcedures: 15,
    equivalencesTxt: 16,
    requirementsTxt: 17,
    coRequirementsTxt: 18,
    syllabus: 19,
    bibliography: 20,
    objectives: 21,
    content: 22,
    skillsCompetencies: 23,
    references: 24,
    programYear: 25,
    programPeriod: 26,
    modality: 27,
    coursesTxt: 28
};

const getModalityTypeFrom = {
    "Presencial": "IN_PERSON",
    "Semi-Presencial": "SEMI_IN_PERSON",
    "A Distância": "ONLINE"
};


function insertFile(file) {
    const subjects = [];
    fs.createReadStream(file)
        .pipe(parse({ delimiter: ";", from_line: 2 }))
        .on("data", (row) => {
            const subject = {
                componentID: row[getSubjectFieldFrom["componentID"]],
                componentType: row[getSubjectFieldFrom["componentType"]],
                code: row[getSubjectFieldFrom["code"]],
                level: row[getSubjectFieldFrom["level"]],
                name: row[getSubjectFieldFrom["name"]],
                department: row[getSubjectFieldFrom["department"]],
                totalHours: Number(row[getSubjectFieldFrom["totalHours"]]),
                equivalencesTxt: row[getSubjectFieldFrom["equivalencesTxt"]],
                requirementsTxt: row[getSubjectFieldFrom["requirementsTxt"]],
                coRequirementsTxt: row[getSubjectFieldFrom["coRequirementsTxt"]],
                coursesTxt: row[getSubjectFieldFrom["coursesTxt"]],
                modality: getModalityTypeFrom[row[getSubjectFieldFrom["modality"]]],
                isActive: true,
                _class: "ufrn.imd.boraPagar.subject.SubjectModel"
            }

            subjects.push(subject);
        })
        .on("end", () => insertDocuments(subjects));
}

async function insertDocuments(documents) {
    const client = new MongoClient(uri, { useNewUrlParser: true, useUnifiedTopology: true });

    try {
        await client.connect();
        console.log("Connected to MongoDB");

        const db = client.db(dbName);
        const collection = db.collection("Subject");
        const result = await collection.insertMany(documents);
        console.log(`${result.insertedCount} documents inserted`);

    } catch (err) {
        console.log("Error:", err);
    } finally {
        await client.close();
        console.log("Connection closed");
    }
}

async function main() {
    for (file of filesToInsert) {
        insertFile(file);
    }
}

main();