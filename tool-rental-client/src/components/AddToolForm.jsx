import GlobalHeader from "../components/GlobalHeader";
import Form from "../components/Form";
import { useState } from "react";
import SuccessAlert from "../components/SuccessAlert";

const AddTool = () => {
    const [status, setStatus] = useState(false);
    const pageTitle = "Add Tool";
    const pageSubTitle = `Let's showcase some tools you might want to rent`;

    const SERVER_URL = "http://localhost:5000";
    const ENDPOINT = "/api/tools/create";
    const METHOD = "POST";

    const handleSubmit = async (formData) => {
        try {
            const res = await fetch(`${SERVER_URL}${ENDPOINT}`, {
                method: METHOD,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(formData),
            });

            res.ok && console.log("Successfully added tool", res.status);
            setStatus(res.ok);
        } catch (err) {
            console.log("Could not create tools", err.message);
        }
    };
    return (
        <>
            <GlobalHeader />
            <Form props={{ pageTitle, pageSubTitle, handleSubmit }} />
            {status && (
                <SuccessAlert
                    urlOnClick={".."}
                    description={"Successfully added tool!"}
                />
            )}
        </>
    );
};

export default AddTool;
