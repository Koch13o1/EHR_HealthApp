import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axiosInstance from "../api/axios";

export default function Vitals() {
    const [form, setForm] = useState({ type: "", value: "" });
    const [vitals, setVitals] = useState([]);
    const navigate = useNavigate();

    const fetchVitals = async () => {
        try {
            const res = await axiosInstance.get("/vitals");
            setVitals(res.data)
        } catch (err) {
            console.error("Failed to fetch vitals", err);
        }
    };

    useEffect(() => {
        fetchVitals();
    }, []);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axiosInstance.post("/vitals", form);
            setForm({ type: "", value: "" });
            fetchVitals();
        } catch (err) {
            alert("Failed to log vital");
            console.error(err);
        }
    };

    return (
        <div className="min-h-screen bg-gray-50 p-8">
            <div className="max-w-xl mx-auto bg-white p-6 rounded shadow">
                <h2 className="text-2xl font-bold mb-4">Log Vital</h2>

                <form onSubmit={handleSubmit} className="space-y-4">
                    <input
                        name="type"
                        type="text"
                        placeholder="e.g. blodd_pressure"
                        value={form.type}
                        onChange={handleChange}
                        className="w-full border px-3 py-2 rounded"
                        required
                    />

                    <input
                        name="value"
                        type="text"
                        placeholder="e.g. 120/80"
                        value={form.value}
                        onChange={handleChange}
                        className="w-full border px-3 py-2 rounded"
                        required
                    />

                    <div className="flex justify-between">
                        <button
                            type="submit"
                            className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600"
                        >
                            Submit
                        </button>
                        <button
                            type="button"
                            onClick={() => navigate("/")}
                            className="bg-gray-300 text-gray-800 px-4 py-2 rounded hover:bg-gray-400"
                        >
                            Back
                        </button>
                    </div>
                </form>
            </div>

            <div className="mt-10 max-w-2xl mx-auto">
                <h3 className="text-xl font-semibold mb-2">Logged Vitals</h3>
                <div className="bg-white p-4 rounded shadow">
                    <table className="w-full table-auto">
                        <thead>
                            <tr className="text-left">
                                <th className="border-b pb-2">Type</th>
                                <th className="border-b pb-2">Value</th>
                                <th className="border-b pb-2">Timestamp</th>
                            </tr>
                        </thead>
                        <tbody>
                            {vitals.map((v, i) => (
                                <tr key={i}>
                                    <td className="py-1">{v.type}</td>
                                    <td className="py-1">{v.value}</td>
                                    <td className="py-1 text-sm text-gray-500">
                                        {new Date(v.timestamp).toLocaleString()}
                                    </td>
                                </tr>
                            ))}
                            {vitals.length === 0 && (
                                <tr>
                                    <td colSpan="3" className="text-center py-4 text-gray-400">
                                        No vitals logged yet.
                                    </td>
                                </tr>
                            )}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}